package pl.stepien.investmentappangular.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.model.entity.CryptoCurrency;
import pl.stepien.investmentappangular.model.entity.Investment;
import pl.stepien.investmentappangular.model.entity.User;
import pl.stepien.investmentappangular.model.request.InvestmentRequest;
import pl.stepien.investmentappangular.repository.InvestmentRepository;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvestmentService {
    private static final Logger log = LogManager.getLogger(InvestmentService.class);
    private final InvestmentRepository investmentRepository;
    private final UserService userService;
    private final CoingeckoService coingeckoService;
    private final CryptoCurrencyService cryptoCurrencyService;
    private final DateService dateService;


    public List<Investment> getAllInvestmentsSortedByIncome(User user) {
        List<Investment> investmentsList = investmentRepository.findAllByUser_Id(user.getId());
        log.info("Loading all investments sorted by income, getAllInvestmentsSortedByIncome()");
        if (investmentsList.isEmpty()) {
            log.warn("List from database is empty. Add investments");
            return investmentsList;
        }
        try {
            return investmentsList.stream()
                    .sorted(Comparator.comparingDouble(Investment::getIncome)).toList();
        } catch (NullPointerException e) {
            log.warn("Outgoing empty list from : getAllInvestmentsSortedByIncome(), check income values");
            return Collections.emptyList();
        }
    }

    public List<Investment> getAllInvestmentsSortedByDate(User user) {
        List<Investment> investmentsList = investmentRepository.findAllByUser_Id(user.getId());
        log.info("Loading all investments sorted by date, getAllInvestmentsSortedByIncome()");
        if (investmentsList.isEmpty()) {
            log.warn("List from database is empty. Add investments");
            return Collections.emptyList();
        }
        try {
            return investmentsList.stream()
                    .sorted(Comparator.comparing(Investment::getCreated).reversed()).toList();
        } catch (NullPointerException e) {
            log.warn("Outgoing empty list from : getAllInvestmentsSortedByCreated(), check created values");
            return Collections.emptyList();
        }
    }

    public Investment get(Long id) {
        log.info("Getting investment by id: {}", id);
        return investmentRepository.findById(id).get();
    }

    public Investment addInvestment(Investment investment) {
        User user = userService.getUserById(1L);
        investment.setUser(user);
        log.info("Saving new investment: {}", investment.getInvestmentName());
        return investmentRepository.save(investment);
    }

    public void deleteInvestment(Long id) {
        log.info("Deleting investment by id: {}", id);
        investmentRepository.deleteById(id);
    }

    public Investment update(Investment investment) {
        log.info("Updating investment: {}", investment.getInvestmentName());
        return investmentRepository.save(investment);
    }

    public Investment setInvestmentParamsAndSave(InvestmentRequest request, String symbol) {
        List<CryptoCurrency> cryptoList = coingeckoService.getCryptoFromApi();
        Optional<CryptoCurrency> optionalCryptoCurrency = cryptoCurrencyService.findCryptoBySymbol(cryptoList, symbol);
        if (optionalCryptoCurrency.isPresent()) {
            CryptoCurrency cryptoCurrency = optionalCryptoCurrency.get();
            Investment investment = new Investment();
            investment.setInvestmentName(request.getInvestmentName());
            investment.setQuantityOfCryptocurrency(request.getQuantityOfCryptocurrency());
            investment.setCreated(dateService.setCurrentDate());
            investment.setEnterPrice(cryptoCurrency.getPrice());
            investment.setUser(userService.getUserById(1L));
            log.info("Creating investment with crypto symbol: {}, setInvestmentParams()", symbol);
            return addInvestment(investment);
        } else {
            log.warn("Crypto currency with symbol : {} NOT FOUND, setInvestmentParams()", symbol);
            return null;
        }
    }

}
