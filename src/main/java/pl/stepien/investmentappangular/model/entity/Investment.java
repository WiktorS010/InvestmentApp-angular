package pl.stepien.investmentappangular.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;


@Entity
@Data
@RequiredArgsConstructor
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
}
