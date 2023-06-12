package com.Franz3.YoutubeTranslator;

import com.Franz3.YoutubeTranslator.downloader.VideoDownloader;

import static com.Franz3.YoutubeTranslator.videoEditing.GetSponsorSegments.getSponsorSegments;
import static com.Franz3.YoutubeTranslator.videoEditing.Helper.launchVideo;

import com.Franz3.YoutubeTranslator.videoEditing.TextToSpeech;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws Exception {
        VideoDownloader.ytDlp("https://youtu.be/eVTXPUF4Oz4");
        //getInformation();
        //getSponsorSegments();
        //TextToSpeech.textToSpeech();
        //edit video
        //launchVideo("C:\\Users\\Franz3\\YoutubeVideoTranslator\\23 CSGO Things 99.7% of Players Don't Know.webm");
        //getTranscript();
    }
}
