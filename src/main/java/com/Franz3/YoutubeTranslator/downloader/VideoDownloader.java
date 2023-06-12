package com.Franz3.YoutubeTranslator.downloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VideoDownloader {
    private static final String path = "C:\\Users\\Franz3\\IdeaProjects\\YoutubeVideoTranslate\\src\\main\\resources\\yt downloader.py";
    public static boolean ytDlp(String arg1) throws IOException, InterruptedException {

        String[] cmd = {
                "python",
                path,
                arg1,
        };
        Process process = Runtime.getRuntime().exec(cmd);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        BufferedReader es = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        String brline;
        String esline;
        while ((brline = br.readLine()) != null) {
            System.out.println(brline);
        }
        while ((esline = es.readLine()) != null) {
            System.out.println(esline);
        }
        int exitValue = process.waitFor();
        System.out.println("finished");
        return exitValue == 0;
    }
}