package helpers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 *
 * @author USER
 */
public class HttpHelper {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private static final String baseUrl = "https://b43b-103-157-48-69.ngrok-free.app/api/v1/";

    public static String get(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(baseUrl + url))
                .build();
        return sendRequest(request);
    }

    public static String post(String url, String jsonBody) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .uri(URI.create(baseUrl + url))
                .setHeader("Content-Type", "application/json")
                .build();
        return sendRequest(request);
    }

    public static String put(String url, String jsonBody) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .uri(URI.create(baseUrl + url))
                .setHeader("Content-Type", "application/json")
                .build();
        return sendRequest(request);
    }

    public static String delete(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
               .uri(URI.create(baseUrl + url))
                .build();
        return sendRequest(request);
    }

    private static String sendRequest(HttpRequest request) throws IOException, InterruptedException {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private static void addHeaders(HttpRequest.Builder builder, Map<String, String> headers) {
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
        }
    }
}
