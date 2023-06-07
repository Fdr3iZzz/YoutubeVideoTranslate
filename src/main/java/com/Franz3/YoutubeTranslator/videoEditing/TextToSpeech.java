package com.Franz3.YoutubeTranslator.videoEditing;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moandjiezana.toml.Toml;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TextToSpeech {
    public static String GetApiKey(){
        File file = new File("C:\\Users\\Franz3\\IdeaProjects\\YoutubeVideoTranslate\\src\\main\\resources\\config.toml");
        Toml toml = new Toml().read(file);
        return toml.getString("elevenlabsApiKey");
    }
    public static class TextToSpeechRequest {
        private String text;
        private String voice_id;
        private VoiceSettings voice_settings;

        public String getVoice_id () {
            return voice_id;
        }

        public void setVoice_id(String voice_id) {
            this.voice_id = voice_id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public VoiceSettings getVoice_settings() {
            return voice_settings;
        }

        public void setVoice_settings(VoiceSettings voice_settings) {
            this.voice_settings = voice_settings;
        }

        public TextToSpeechRequest(String voice_id, String text, VoiceSettings voice_settings) {
            this.voice_id = voice_id;
            this.text = text;
            this.voice_settings = voice_settings;
        }
    }

    public static class VoiceSettings {
        private double stability;
        private double similarity_boost;
        public VoiceSettings(int stability, int similarity_boost) {
            this.stability = stability;
            this.similarity_boost = similarity_boost;
        }

        public double getStability() {
            return stability;
        }

        public void setStability(double stability) {
            this.stability = stability;
        }

        public double getSimilarity_boost() {
            return similarity_boost;
        }

        public void setSimilarity_boost(double similarity_boost) {
            this.similarity_boost = similarity_boost;
        }
    }
        public static void mainTextToSpeech(String textInput) throws IOException, InterruptedException {
        String path = "C:\\Users\\Franz3\\Desktop\\";
            VoiceSettings voiceSettings = new VoiceSettings(0, 0);
            TextToSpeechRequest request = new TextToSpeechRequest("ErXwobaYiN019PkySvjV", textInput, voiceSettings);

            ObjectMapper mapper = new ObjectMapper();
            String requestBody = mapper.writeValueAsString(request);
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.elevenlabs.io/v1/text-to-speech/ErXwobaYiN019PkySvjV/stream"))
                    .header("accept", "audio/mpeg")
                    .header("xi-api-key", GetApiKey())
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<byte[]> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofByteArray());
            Files.write(Path.of(path + "audio.mp3"), httpResponse.body(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}