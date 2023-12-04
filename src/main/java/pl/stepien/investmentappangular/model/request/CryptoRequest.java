package pl.stepien.investmentappangular.model.request;

import lombok.Data;

@Data
public class CryptoRequest {
    private String name;
    private String symbol;
    private Long marketCapRank;
    private Double price;
    private String priceString;
    private Double dayChange;
    private Long marketCap;
    private String marketCapString;

}
