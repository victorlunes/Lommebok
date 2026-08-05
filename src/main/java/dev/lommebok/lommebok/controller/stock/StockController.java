package dev.lommebok.lommebok.controller.stock;

import dev.lommebok.lommebok.doc.controller.stock.StockControllerDoc;
import dev.lommebok.lommebok.dto.stock.brabi.Result.StockResult;
import dev.lommebok.lommebok.service.stock.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/stocks")
public class StockController implements StockControllerDoc {
    private final StockService stockService;

    public StockController(StockService service) {
        this.stockService = service;
    }

    @GetMapping("/{tickers}")
    public ResponseEntity<List<StockResult>> lookForActions(@PathVariable String tickers) {
        List<StockResult> resultado = stockService.getAQuote(tickers);
        return ResponseEntity.ok(resultado);
    }

}
