package pl.stepien.investmentappangular.model.request;

import lombok.Data;


@Data
public class InvestmentRequest {
    private String investmentName;
    private Double quantityOfCryptocurrency;

}
