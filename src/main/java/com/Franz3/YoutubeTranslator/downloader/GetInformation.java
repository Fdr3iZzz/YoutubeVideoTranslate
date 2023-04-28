package com.Franz3.YoutubeTranslator.downloader;

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
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;
public class GetInformation {
        private static final String CLIENT_SECRETS= "C:\\Users\\Franz3\\IdeaProjects\\YoutubeVideoTranslate\\src\\main\\resources\\client_secret.json";
        private static final Collection<String> SCOPES =
                Arrays.asList("https://www.googleapis.com/auth/youtube.readonly");

        private static final String APPLICATION_NAME = "YoutubeVideoTranslator";
        private static final File file = new File(CLIENT_SECRETS);
        private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

        /**
         * Create an authorized Credential object.
         *
         * @return an authorized Credential object.
         * @throws IOException
         */
        public static Credential authorize(final NetHttpTransport httpTransport) throws IOException {
            // Load client secrets.
            InputStream in = new FileInputStream(file);
            GoogleClientSecrets clientSecrets =
                    GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            // Build flow and trigger user authorization request.
            GoogleAuthorizationCodeFlow flow =
                    new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                            .build();
            Credential credential =
                    new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
            return credential;
        }

        /**
         * Build and return an authorized API client service.
         *
         * @return an authorized API client service
         * @throws GeneralSecurityException, IOException
         */
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
         *
         * @throws GeneralSecurityException, IOException, GoogleJsonResponseException
         */
        public static void getInformation()
                throws GeneralSecurityException, IOException, GoogleJsonResponseException {
            YouTube youtubeService = getService();
            // Define and execute the API request
            YouTube.Videos.List request = youtubeService.videos()
                    .list("snippet,contentDetails,statistics");
            VideoListResponse response = request.setId("Ks-_Mh1QhMc").execute();
            System.out.println(response);
        }
}
