package pl.stepien.investmentappangular.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.model.entity.CryptoCurrency;
import pl.stepien.investmentappangular.model.entity.Investment;
import pl.stepien.investmentappangular.model.entity.User;
import pl.stepien.investmentappangular.model.exception.customExceptions.CryptocurrnecyNotFoundException;
import pl.stepien.investmentappangular.model.exception.customExceptions.InternalServerErrorException;
import pl.stepien.investmentappangular.model.exception.customExceptions.InvestmentListNotFoundException;
import pl.stepien.investmentappangular.model.exception.customExceptions.InvestmentNotFoundException;
import pl.stepien.investmentappangular.model.request.InvestmentRequest;
import pl.stepien.investmentappangular.repository.InvestmentRepository;


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


    public List<Investment> getAllInvestmentsSortedByDate(User user) {
        try {
            List<Investment> investmentsList = investmentRepository.findAllByUser_Id(user.getId());
            if (investmentsList == null) {
                log.warn("Investment list is null, check if user exists and database, getAllInvestmentsSortedByDate()");
                throw new InvestmentListNotFoundException();
            } else if (investmentsList.isEmpty()) {
                log.info("Investment list is empty, add investments for user to database, getAllInvestmentsSortedByDate()");
                return investmentsList;
            } else
                log.info("Sorting investment list, getAllInvestmentsSortedByDate()");
            return investmentsList.stream()
                    .sorted(Comparator.comparing(Investment::getCreated).reversed()).toList();
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

    public Investment get(Long id) {
        if (investmentRepository.findById(id).isPresent()) {
            log.info("Getting investment by id: {}", id);
            return investmentRepository.findById(id).get();
        } else {
            throw new InvestmentNotFoundException(id);
        }
    }

    public Investment addInvestment(Investment investment) {
        User user = userService.getUserById(1L);
        if (user == null) {
            log.warn("There is no user with id = 1, check ur database");
            return null;
        } else {
            investment.setUser(user);
            log.info("Saving new investment: {}", investment.getInvestmentName());
            return investmentRepository.save(investment);
        }
    }

    public void deleteInvestment(Long id) {
        if(investmentRepository.findById(id).isPresent()){
            log.info("Deleting investment by id: {}", id);
            investmentRepository.deleteById(id);
        } else {
            log.warn("No investment with id: {} in database", id);
        }
    }

    public Investment update(Investment investment) {
        if(investmentRepository.findById(investment.getId()).isPresent()){
            log.info("Updating investment: {}", investment.getInvestmentName());
            return investmentRepository.save(investment);
        } else {
            log.warn("No investment with id: {} in database", investment.getId());
            return null;
        }
    }

    public Investment setInvestmentParamsAndSave(InvestmentRequest request, String symbol) {
        try {
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
                throw new CryptocurrnecyNotFoundException(symbol);
            }
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
    }

}
