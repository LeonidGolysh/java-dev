package org.example;

import java.io.IOException;
import java.util.Scanner;

public class HttpImageStatusCli {
    private final HttpStatusDownloader downloader;

    public HttpImageStatusCli() {
        this.downloader = new HttpStatusDownloader();
    }

    public void askStatus() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter HTTP status code (or 'q' to quit): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            try {
                int code = Integer.parseInt(input);
                try {
                    downloader.downloadStatusImage(code);
                    System.out.println("Image download.");
                } catch (IOException e) {
                    System.err.println("Exception: " + e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number");
            }
        }
        scanner.close();
    }
}
