package com.Franz3.YoutubeTranslator.downloader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.CaptionListResponse;
import com.google.api.services.youtube.model.ChannelListResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;

import static com.Franz3.YoutubeTranslator.videoEditing.Helper.extractVideoId;

public class GetInformation {
    private static final String videoId = extractVideoId("https://youtu.be/bJ3Dg62JM8w");
    private static final String CLIENT_SECRETS= "C:\\Users\\Franz3\\IdeaProjects\\YoutubeVideoTranslate\\src\\main\\resources\\client_secret.json";
    private static final Collection<String> SCOPES =
            Arrays.asList("https://www.googleapis.com/auth/youtube.force-ssl");

    private static final String APPLICATION_NAME = "YoutubeVideoTranslator";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

     // @return an authorized Credential object.
    public static Credential authorize(final NetHttpTransport httpTransport) throws IOException {
        // Load client secrets.
        InputStream inputStream = Files.newInputStream(Paths.get(CLIENT_SECRETS));
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(inputStream));
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                        .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    // @return an authorized API client service
    public static YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = authorize(httpTransport);
        return new YouTube.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Call function to create API service object. Define and
     * execute API request. Print API response.
     */
    public static void getTranscript() throws GeneralSecurityException, IOException, GoogleJsonResponseException {
        YouTube youtubeService = getService();

        OutputStream output = new FileOutputStream("C:\\Users\\Franz3\\Desktop\\caption.json");
        YouTube.Captions.List caption = youtubeService.captions().list("id", videoId);
        CaptionListResponse response = caption.execute();
        System.out.println(response);

        YouTube.Captions.Download request = youtubeService.captions().download(getVideoId(response)).setTfmt("vtt").setTlang("en");
        request.getMediaHttpDownloader();
        request.executeMediaAndDownloadTo(output);
    }
    public static String getVideoId(CaptionListResponse jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(String.valueOf(jsonString));
        JsonNode itemsNode = rootNode.get("items");
        JsonNode firstItemNode = itemsNode.get(0);
        return firstItemNode.get("id").asText();
    }
}