package pl.stepien.investmentappangular.service;

import pl.stepien.investmentappangular.model.Investment;
import pl.stepien.investmentappangular.model.User;
import pl.stepien.investmentappangular.repository.CryptoCurrencyRepository;
import pl.stepien.investmentappangular.repository.InvestmentRepository;

import java.util.Comparator;
import java.util.List;

public class IvestmentService {
    private final InvestmentRepository investmentRepository;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    public IvestmentService(InvestmentRepository investmentRepository, CryptoCurrencyRepository cryptoCurrencyRepository) {
        this.investmentRepository = investmentRepository;
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
    }
    public List<Investment> getAllInvestmentsSortedByIncome(User user){
        List<Investment> investmentsList = investmentRepository.findAllByUser_Id(user.getId());
                investmentsList.sort(Comparator.comparingDouble(investment -> investment.calculateIncome(cryptoCurrencyRepository.findCryptoCurrencyByInvestment_Id(investment.getId()))));
        return investmentsList;
    }


}
