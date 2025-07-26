package service;

import dto.QuoteResponse;

import java.util.concurrent.ExecutionException;

public class QuoteService {
    private ZenQuoteService zenQuoteService;
    private TranslateService translateService;

    public static QuoteResponse getQuote() throws ExecutionException, InterruptedException {
        QuoteResponse quoteResponse = ZenQuoteService.getQuote();
        quoteResponse.setQuote(TranslateService.translateText(quoteResponse.getQuote()));
        return quoteResponse;
    }
}
