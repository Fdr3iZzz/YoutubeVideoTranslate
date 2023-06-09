package com.Franz3.YoutubeTranslator.videoEditing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public static String extractVideoId(String url) {
        String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(url);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
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
}
