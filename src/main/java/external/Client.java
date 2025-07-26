package external;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class Client {
    private static HttpClient client;
    private static HttpRequest.Builder request;
    private Client() {}

    public static HttpClient getClient() {
        if (client == null) {
            client = HttpClient.newHttpClient();
        }
        return client;
    }

    public static HttpRequest.Builder getHttpRequestBuilder() {
        return HttpRequest.newBuilder();
    }
}
