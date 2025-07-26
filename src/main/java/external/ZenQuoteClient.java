package external;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ZenQuoteClient {
    private static final String baseUrl = "https://zenquotes.io/api/random";

    public static CompletableFuture<HttpResponse<String>> getResponseQuote() {
        HttpClient client = Client.getClient();
        HttpRequest request = Client.getHttpRequestBuilder()
                .uri(URI.create(baseUrl))
                .GET()
                .build();

        return  client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

}
