package dev.lommebok.lommebok.dto.stock.brabi.Result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockResult {
    private String symbol;
    private String shortName;
    private String longName;
    private String currency;
    private Double regularMarketPrice;
    private Double regularMarketDayHigh;
    private Double regularMarketDayLow;
    private String regularMarketDayRange;
    private Double regularMarketChange;
    private Double regularMarketChangePercent;
    private String regularMarketTime;
    private Long marketCap;
    private Long regularMarketVolume;
    private Double regularMarketPreviousClose;
    private Double regularMarketOpen;
    private String fiftyTwoWeekRange;
    private Double fiftyTwoWeekLow;
    private Double fiftyTwoWeekHigh;
    private String logourl;
}
