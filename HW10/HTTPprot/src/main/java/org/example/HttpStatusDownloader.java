package org.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStatusDownloader {
    public static void downloadStatusImage(int code) throws IOException {
        String imageUrl = HttpStatusChecker.getStatusImage(code);

        if (imageUrl == null) {
            throw new IOException("Image not found for status code: " + code);
        }

        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Failed to download image. Status code: " + connection.getResponseCode());
        }

        try (InputStream in = connection.getInputStream();
             FileOutputStream out = new FileOutputStream(code + ".jpg")) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0,bytesRead);
            }
        } finally {
            connection.disconnect();
        }
    }
}
