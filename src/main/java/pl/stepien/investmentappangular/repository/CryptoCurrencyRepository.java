package pl.stepien.investmentappangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.stepien.investmentappangular.model.CryptoCurrency;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
    CryptoCurrency findCryptoCurrencyByInvestment_Id(Long investments_id);
}
