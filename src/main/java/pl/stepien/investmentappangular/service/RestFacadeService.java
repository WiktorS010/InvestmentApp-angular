package pl.stepien.investmentappangular.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.controller.InvestmentController;
import pl.stepien.investmentappangular.model.entity.CryptoCurrency;
import pl.stepien.investmentappangular.model.entity.Investment;
import pl.stepien.investmentappangular.model.request.CryptoRequest;
import pl.stepien.investmentappangular.model.request.InvestmentRequest;


import java.util.List;

@Service
@RequiredArgsConstructor
public class RestFacadeService {
    private static final Logger log = LogManager.getLogger(InvestmentController.class);
    private final CoingeckoService coingeckoService;
    private final InvestmentService investmentService;
    private final UserService userService;
    private final CryptoCurrencyService cryptoCurrencyService;
    private final CryptoRequestService cryptoRequestService;
    private String symbolToShare;

    public List<CryptoCurrency> getCryptoListResponse() {
        List<CryptoCurrency> cryptoCurrencyList = coingeckoService.getCryptoFromApi();
        cryptoCurrencyService.setCryptocurrencyAttributes(cryptoCurrencyList);
        log.info("CryptoCurrency List ready to go from getCryptoListResponse()");
        return cryptoCurrencyList;
    }


    public void sendInfoFromCryptoList(CryptoRequest request) {
        cryptoRequestService.checkCryptoRequestSymbolValue(request);
        this.symbolToShare = request.getSymbol();
        log.info("Symbol received by sendInfoFromCryptoList(), symbolToShare setting: {}", symbolToShare);
    }

    public String getSymbolToShare() {
        return symbolToShare;
    }

    public List<Investment> getSortedInvestmentsResponse() {
        log.info("Investments List sorted ready to go from getSortedInvestmentsResponse()");
        return investmentService.getAllInvestmentsSortedByDate(userService.getUserById(1L));
    }

    public CryptoCurrency saveInvestmentPageView() {
        String symbol = getSymbolToShare();

        log.info("Investment view ready in saveInvestmentPageView(), investment with symbol: {}", symbol);
        return cryptoCurrencyService.getCryptoCurrencyForInvestment(symbol);

    }

    public Investment processSaveInvestment(InvestmentRequest request) {
        String symbol = getSymbolToShare();
        log.info("Investment ready to save in processSaveInvestment(), investment with symbol: {}", symbol);
        return investmentService.setInvestmentParamsAndSave(request, symbol);

    }

    public Investment getInvestmentIdView(Long id) {
        log.info("Getting investment from database, getInvestmentIdView(), investment id: {}", id);
        return investmentService.get(id);
    }

}
