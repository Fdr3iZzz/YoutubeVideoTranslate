package com.Franz3.YoutubeTranslator.videoEditing;

import java.io.File;
import java.io.IOException;

public class EditVideo {
    private static String filePath = "C:\\Users\\Franz3\\YoutubeVideoTranslator\\In The End [Official HD Music Video] - Linkin Park.webm";
    private static String ffmpegPath = "E:\\Software\\ffmpeg\\ffmpeg.exe";

    public static void setFilePath(String filePath) {
        EditVideo.filePath = filePath;
    }

    public static void deleteAudio() {
        File file = new File(filePath);
        setFilePath(filePath.substring(0, filePath.lastIndexOf(".webm")));
            String command = ffmpegPath + " -i \"" + file.getAbsolutePath() + "\" -c:v copy -an \"" + filePath + "-noSound.webm";
            ProcessBuilder pb = new ProcessBuilder(command.split(" "));
            try {
                Process p = pb.start();
                p.waitFor();
                System.out.println("Removed audio from " + file.getName());
                file.delete();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    public static void addAudio() {
        setFilePath(filePath.substring(0, filePath.lastIndexOf(".webm")));
        String webmPath = filePath + "-noSound.webm";
        System.out.println(webmPath);
        String mp3Path = "C:\\Users\\Franz3\\YoutubeVideoTranslator\\audio.mp3";

        String command = ffmpegPath + " -i \"" + webmPath + "\" -i \"" + mp3Path + "\" -c:v copy -c:a libopus -map 0:v:0 -map 1:a:0 \"" + filePath + ".webm";
        ProcessBuilder pb = new ProcessBuilder(command.split(" "));
        try {
            Process p = pb.start();
            p.waitFor();
            System.out.println("Added audio to " + webmPath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
