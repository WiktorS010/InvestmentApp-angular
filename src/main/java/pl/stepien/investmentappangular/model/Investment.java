package pl.stepien.investmentappangular.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.ZonedDateTime;

@Entity
@Data
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantityOfCryptocurrency;
    private ZonedDateTime created;
    private double enterPrice;
    public double calculateIncome(CryptoCurrency cryptoCurrency){
        return quantityOfCryptocurrency*cryptoCurrency.getPrice() - quantityOfCryptocurrency*enterPrice;
    }
    @OneToOne
    private CryptoCurrency cryptoCurrency;
    @OneToOne
    private User user;
//    private Duration beingActive; private ZonedDateTime created;
}
