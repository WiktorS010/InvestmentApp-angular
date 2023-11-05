package pl.stepien.investmentappangular.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.model.Investment;
import pl.stepien.investmentappangular.model.User;
import pl.stepien.investmentappangular.repository.CryptoCurrencyRepository;
import pl.stepien.investmentappangular.repository.InvestmentRepository;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class InvestmentService {
    private final InvestmentRepository investmentRepository;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final UserService userService;

    public InvestmentService(InvestmentRepository investmentRepository, CryptoCurrencyRepository cryptoCurrencyRepository, UserService userService) {
        this.investmentRepository = investmentRepository;
        this.cryptoCurrencyRepository = cryptoCurrencyRepository;
        this.userService = userService;
    }

    public List<Investment> getAllInvestmentsSortedByIncome(User user) {
        log.info("Loading all investments sorted by income");
        List<Investment> investmentsList = investmentRepository.findAllByUser_Id(user.getId());
        if (investmentsList.isEmpty()) {
            throw new IllegalArgumentException("List from database is empty. Add investments");
        }
        return investmentsList.stream()
                .sorted(Comparator.comparingDouble(Investment::getIncome)).toList();
    }

    public Investment get(Long id) {
        log.info("Getting investment by id: {}", id);
        return investmentRepository.findById(id).get();
    }

    public Investment addInvestment(Investment investment) {
//        USER ID ALWAYS 1 FOR NOW
        User user = userService.getUserById(1L);
        investment.setUser(user);
        log.info("Saving new investment: {}", investment.getCryptoCurrency());
        return investmentRepository.save(investment);
    }

    public void deleteInvestment(Long id) {
        log.info("Deleting investment by id: {}", id);
        investmentRepository.deleteById(id);
    }

    public Investment update(Investment investment) {
        log.info("Updating investment: {}", investment.getCryptoCurrency());
        return investmentRepository.save(investment);
    }


}
