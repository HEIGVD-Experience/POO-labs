package engine.fen;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Just a tool to export our board to an image which will be automatically added to our documentation.
 */
public class FENImageDownloader {

    /**
     * Generate and save the FEN position images to a PNG using a web API.
     *
     * @param FENCode the FEN code
     * @param filePath the file path
     * @return true if it was successful.
     */
    public static boolean SaveToPNG(String FENCode, String filePath) {
        try {
            String encodedFEN = URLEncoder.encode(FENCode, StandardCharsets.UTF_8).replace("+", "%20");

            // Construct the URL with the FEN notation
            String urlString = "https://fen2image.chessvision.ai/" + encodedFEN;

            // Create the directory if it doesn't exist
            Path directory = Path.of(filePath).getParent();
            if (Files.notExists(directory)) {
                Files.createDirectories(directory);
            }

            // Create an HttpClient
            HttpClient httpClient = HttpClient.newHttpClient();

            // Create an HttpRequest
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlString))
                    .header("User-Agent", "Mozilla/5.0")
                    .build();

            // Send the request and retrieve the response
            HttpResponse<Path> response = httpClient.send(request, HttpResponse.BodyHandlers.ofFile(Path.of(filePath)));

            // Get the response code
            int responseCode = response.statusCode();

            // Check if the request was successful (HTTP 200 OK)
            // File saved successfully
            return responseCode == 200;

        } catch (IOException | InterruptedException e) {
            // An error occurred
            return false;
        }
    }
}
