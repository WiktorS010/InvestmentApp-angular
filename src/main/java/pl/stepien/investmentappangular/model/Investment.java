package pl.stepien.investmentappangular.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;


@Entity
@Data
@NoArgsConstructor
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String investmentName;
    private Double quantityOfCryptocurrency;
    private ZonedDateTime created;
    private Double enterPrice;
    private Double income;
    @OneToOne
    private CryptoCurrency cryptoCurrency;
    @ManyToOne
    private User user;
    //    private Duration beingActive; private ZonedDateTime created;

    public Investment(Long id, String investmentName, Double quantityOfCryptocurrency, ZonedDateTime created, Double enterPrice, Double income, CryptoCurrency cryptoCurrency, User user) {
        this.id = id;
        this.investmentName = investmentName;
        this.quantityOfCryptocurrency = quantityOfCryptocurrency;
        this.created = created;
        this.enterPrice = enterPrice;
        this.income = income;
        this.cryptoCurrency = cryptoCurrency;
        this.user = user;
    }

    // Constructor for testing
    public Investment(Long id, Double income, User user) {
        this.id = id;
        this.income = income;
        this.user = user;
    }

}
