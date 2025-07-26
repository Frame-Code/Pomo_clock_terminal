package external;

import com.google.gson.Gson;
import dto.QuoteResponse;
import dto.TranslateRequest;
import service.ZenQuoteService;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class LibreTranslateClient {
    private static final Gson gson = new Gson();
    private static final String baseUrl = "http://localhost:8600/translate";
    private static TranslateRequest translateRequest = new TranslateRequest("en", "es");

    public static CompletableFuture<HttpResponse<String>> translateText(String text) {
        translateRequest.setQ(text);
        HttpClient client = Client.getClient();
        HttpRequest request = Client.getHttpRequestBuilder()
                .uri(URI.create(baseUrl))
                .timeout(Duration.ofSeconds(30))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(translateRequest)))
                .build();

        return  client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

}
