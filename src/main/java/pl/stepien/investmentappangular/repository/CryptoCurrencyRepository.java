package pl.stepien.investmentappangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.stepien.investmentappangular.model.CryptoCurrency;
@Repository
public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {
    CryptoCurrency findCryptoCurrencyByInvestment_Id(Long investments_id);
    CryptoCurrency findBySymbol(String symbol);
}
