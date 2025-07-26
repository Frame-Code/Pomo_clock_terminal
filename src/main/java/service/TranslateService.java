package service;

import com.google.gson.Gson;
import dto.QuoteResponse;
import dto.TranslatedText;
import external.LibreTranslateClient;

import java.util.concurrent.ExecutionException;

public class TranslateService {
    private static final Gson gson = new Gson();
    private static TranslatedText translatedText = new TranslatedText();

    public static String translateText(String text) throws ExecutionException, InterruptedException {
        String json = LibreTranslateClient.translateText(text)
                .get()
                .body();
        translatedText = gson.fromJson(json, TranslatedText.class);
        return translatedText.getText();
    }

}
