package com.Franz3.YoutubeTranslator.downloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VideoDownloader {
    private static final String path = "C:\\Users\\Franz3\\IdeaProjects\\YoutubeVideoTranslate\\src\\main\\resources\\yt downloader.py";
    public static boolean ytDlp() throws IOException, InterruptedException {
        String arg1 = "https://www.youtube.com/watch?v=WNeLUngb-Xg";
        String[] cmd = {
                "python",
                path,
                arg1,
        };
        Process process = Runtime.getRuntime().exec(cmd);
        process.waitFor();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }


        return false;
        //return Boolean.parseBoolean(output);
    }
}