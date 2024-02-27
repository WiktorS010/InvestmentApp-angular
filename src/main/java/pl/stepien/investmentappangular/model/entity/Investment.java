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
    @ManyToOne
    private User user;
    public Investment(String investmentName, Double quantityOfCryptocurrency, ZonedDateTime created, Double enterPrice, Double income) {
        this.investmentName = investmentName;
        this.quantityOfCryptocurrency = quantityOfCryptocurrency;
        this.created = created;
        this.enterPrice = enterPrice;
        this.income = income;
    }

    public static InvestmentBuilder builder() {
        return new InvestmentBuilder();
    }

    public static class InvestmentBuilder {
        private String investmentName;
        private Double quantityOfCryptocurrency;
        private ZonedDateTime created;
        private Double enterPrice;
        private Double income;

        public InvestmentBuilder investmentName(final String investmentName) {
            this.investmentName = investmentName;
            return this;
        }

        public InvestmentBuilder quantityOfCryptocurrency(final Double quantityOfCryptocurrency) {
            this.quantityOfCryptocurrency = quantityOfCryptocurrency;
            return this;
        }

        public InvestmentBuilder created(final ZonedDateTime created) {
            this.created = created;
            return this;
        }

        public InvestmentBuilder enterPrice(final Double enterPrice) {
            this.enterPrice = enterPrice;
            return this;
        }

        public InvestmentBuilder income(final Double income) {
            this.income = income;
            return this;
        }

        public Investment build() {
            return new Investment(investmentName, quantityOfCryptocurrency, created, enterPrice, income);
        }
    }
}
