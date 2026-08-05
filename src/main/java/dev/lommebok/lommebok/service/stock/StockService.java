package dev.lommebok.lommebok.service.stock;

import dev.lommebok.lommebok.dto.stock.brabi.Response.BrapiResponse;
import dev.lommebok.lommebok.dto.stock.brabi.Result.StockResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class StockService {

    private final RestClient restClient;

    public StockService(RestClient restClient) {
        this.restClient = restClient;
    }

    public List<StockResult> getAQuote(String tickers) {
        BrapiResponse response = restClient.get()
                .uri("/quote/{tickers}", tickers)
                .retrieve()
                .body(BrapiResponse.class);

        return response != null ? response.getResults() : List.of();
    }
}
