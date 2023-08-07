package pl.stepien.investmentappangular.service;

import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.model.Investment;
import pl.stepien.investmentappangular.model.User;
import pl.stepien.investmentappangular.repository.CryptoCurrencyRepository;
import pl.stepien.investmentappangular.repository.InvestmentRepository;

import java.util.Comparator;
import java.util.List;

@Service
public class InvestmentService {
    private final InvestmentRepository investmentRepository;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    public InvestmentService(InvestmentRepository investmentRepository, CryptoCurrencyRepository cryptoCurrencyRepository) {
        this.investmentRepository = investmentRepository;
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
    }

    public List<Investment> getAllInvestmentsSortedByIncome(User user) {
        List<Investment> investmentsList = investmentRepository.findAllByUser_Id(user.getId());
        return investmentsList.stream()
                .sorted(Comparator.comparingDouble(Investment::getIncome)).toList();
    }

    public void addInvestment(Investment investment) {
        investmentRepository.save(investment);
    }

    public void deleteInvestment(Long id) {
        investmentRepository.deleteById(id);
    }


}
