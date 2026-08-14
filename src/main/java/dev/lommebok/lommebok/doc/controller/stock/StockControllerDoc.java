package dev.lommebok.lommebok.doc.controller.stock;

import dev.lommebok.lommebok.dto.stock.brabi.Result.StockResult;
import dev.lommebok.lommebok.infra.RestErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(
        name = "stock",
        description = "Endpoint responsible for retrieving stock quotes"
)
public interface StockControllerDoc {

    @Operation(
            summary = "Search for stock quotes",
            description = "Endpoint for retrieving stock quotes by ticker symbol"
    )
    @ApiResponse(
            responseCode = "200",
            description = "stock quotes retrieved successfully.",
            content = @Content(
                    array = @ArraySchema(schema = @Schema(implementation = StockResult.class)),
                    examples = @ExampleObject(
                            name = "stock quotes",
                            value = "[{\"symbol\": \"PETR4\", \"shortName\": \"PETROBRAS PN N2\", "
                                    + "\"longName\": \"Petróleo Brasileiro S.A. - Petrobras\", \"currency\": \"BRL\", "
                                    + "\"regularMarketPrice\": 32.50, \"regularMarketDayHigh\": 33.10, "
                                    + "\"regularMarketDayLow\": 32.20, \"regularMarketDayRange\": \"32.20 - 33.10\", "
                                    + "\"regularMarketChange\": 0.35, \"regularMarketChangePercent\": 1.09, "
                                    + "\"regularMarketTime\": \"2026-08-13T17:07:00.000Z\", "
                                    + "\"marketCap\": 425000000000, \"regularMarketVolume\": 24500000, "
                                    + "\"regularMarketPreviousClose\": 32.15, \"regularMarketOpen\": 32.40, "
                                    + "\"fiftyTwoWeekRange\": \"27.20 - 42.94\", \"fiftyTwoWeekLow\": 27.20, "
                                    + "\"fiftyTwoWeekHigh\": 42.94, "
                                    + "\"logourl\": \"https://s3-symbol-logo.tradingview.com/brasileiro-petrobras--big.svg\"}]"
                    )
            )
    )
    @ApiResponse(
            responseCode = "500",
            description = "error retrieving stock quotes.",
            content = @Content(
                    schema = @Schema(implementation = RestErrorMessage.class),
                    examples = @ExampleObject(
                            name = "internal error",
                            value = "{\"message\": \"Erro interno inesperado.\", \"status\": \"500 INTERNAL_SERVER_ERROR\"}"
                    )
            )
    )
    ResponseEntity<List<StockResult>> lookForActions(@PathVariable("tickers") String tickers);
}
