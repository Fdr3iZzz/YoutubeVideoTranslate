package com.Franz3.YoutubeTranslator.videoEditing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;

public class GetSponsorSegments {
    public static void getSponsorSegments() throws Exception {

        String jsonString = Files.readString(Paths.get("C:\\Users\\Franz3\\YoutubeVideoTranslator\\sponsorblockChapters.json"));
        ObjectMapper objectMapper = new ObjectMapper();
        Event[] events = objectMapper.readValue(jsonString, Event[].class);

        for(Event event : events){
            double startTime = event.getStart_time();
            double endTime = event.getEnd_time();
            System.out.println("start_time: " + startTime);
            System.out.println("end_time: " + endTime);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Event {
        private double start_time;
        private double end_time;

        public double getStart_time() {
            return start_time;
        }
        public double getEnd_time() {
            return end_time;
        }
    }
}
