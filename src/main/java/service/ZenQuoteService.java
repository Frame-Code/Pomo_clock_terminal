package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.QuoteResponse;
import external.ZenQuoteClient;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ZenQuoteService {
    private static final Gson gson = new Gson();
    private static final Type listType = new TypeToken<List<QuoteResponse>>(){}.getType();

    public static QuoteResponse getQuote() throws ExecutionException, InterruptedException {
        return processResponse(
                ZenQuoteClient.getResponseQuote()
                .get()
                .body());
    }

    private static QuoteResponse processResponse(String plainResponse) {
        List<QuoteResponse> list = gson.fromJson(plainResponse, listType);
        return list.get(0);
    }

}
