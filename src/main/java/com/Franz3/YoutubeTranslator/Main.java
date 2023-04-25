package com.Franz3.YoutubeTranslator;

import com.Franz3.YoutubeTranslator.downloader.VideoDownloader;
import com.Franz3.YoutubeTranslator.voiceCreation.TextToSpeech;


public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(VideoDownloader.ytDlp());
        //TextToSpeech.mainTextToSpeech("Hello, this is a test");
    }
}
