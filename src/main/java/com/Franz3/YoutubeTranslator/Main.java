package com.Franz3.YoutubeTranslator;

import com.Franz3.YoutubeTranslator.downloader.VideoDownloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws Exception {
        //TextToSpeech.mainTextToSpeech("Hello, this is a test");
        //if (VideoDownloader.ytDlp()){
            launchVideo("C:\\Users\\Franz3\\YoutubeVideoTranslator\\test.webm");
       // }
    }

    public static void launchVideo(String path) throws IOException {
        boolean generalLogs = true;
        boolean errorLogs = true;
        ProcessBuilder processBuilder = new ProcessBuilder("mpv", path);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        if (generalLogs){
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = inputStream.readLine()) != null) {
                System.out.println(line);
            }
        }
        if (errorLogs){
            BufferedReader errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line;
            while ((line = errorStream.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
