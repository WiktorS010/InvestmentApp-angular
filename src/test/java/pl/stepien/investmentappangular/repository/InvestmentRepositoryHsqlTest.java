package pl.stepien.investmentappangular.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.stepien.investmentappangular.model.entity.Investment;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
public class InvestmentRepositoryHsqlTest {
    @Autowired
    private InvestmentRepository investmentRepository;

    @BeforeEach
    void setUp() {
        // Given: Setup object
        ZonedDateTime myZonedDateTime = ZonedDateTime.of(2023, 1, 1, 12, 0, 0, 0, ZoneId.of("UTC"));

        Investment investment1 = Investment.builder()
                .investmentName("Bitcoin Investment")
                .quantityOfCryptocurrency(10.0)
                .created(myZonedDateTime)
                .enterPrice(42700.0)
                .income(100.0)
                .build();

        Investment investment2 = Investment.builder()
                .investmentName("Ethereum Investment")
                .quantityOfCryptocurrency(5.0)
                .created(myZonedDateTime)
                .enterPrice(2200.0)
                .income(100.0)
                .build();

        Investment investment3 = Investment.builder()
                .investmentName("XRP Investment")
                .quantityOfCryptocurrency(100.0)
                .created(myZonedDateTime)
                .enterPrice(240.5)
                .income(100.0)
                .build();

        investmentRepository.saveAllAndFlush(List.of(
                investment1,
                investment2,
                investment3
        ));
    }
    // NOT WORKING , SMTH WRONG WITH HIBERNATE OR SPRING CONFIGURATION.
    @Test
    public void givenInvestmentList_whenFindAll_thenInvestmentList() {
        List<Investment> investments = investmentRepository.findAll();
        assertThat(investments).hasSize(3);

    }


}