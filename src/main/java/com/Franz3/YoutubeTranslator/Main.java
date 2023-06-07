package com.Franz3.YoutubeTranslator;

import com.Franz3.YoutubeTranslator.downloader.VideoDownloader;
import static com.Franz3.YoutubeTranslator.videoEditing.GetSponsorSegments.getSponsorSegments;

import com.Franz3.YoutubeTranslator.videoEditing.TextToSpeech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void launchVideo(String path) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("mpv", path);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String inputLine;
        while ((inputLine = inputStream.readLine()) != null) {
            System.out.println(inputLine);
        }
        BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String errorLine;
        while ((errorLine = errorStream.readLine()) != null) {
            System.out.println(errorLine);
        }
    }

    public static void main(String[] args) throws Exception {
        //VideoDownloader.ytDlp();
        //getInformation();
        getSponsorSegments();
        //TextToSpeech.mainTextToSpeech("");
        //edit video
        //launchVideo("C:\\Users\\Franz3\\YoutubeVideoTranslator\\23 CSGO Things 99.7% of Players Don't Know.webm");
    }
}
