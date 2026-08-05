package dev.lommebok.lommebok.dto.stock.brabi.Response;

import dev.lommebok.lommebok.dto.stock.brabi.Result.StockResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrapiResponse {
    private List<StockResult> results;
    private String requestedAt;
    private Integer took;
}
