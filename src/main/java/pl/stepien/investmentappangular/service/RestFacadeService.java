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
    private String symbolToShare;

    public List<CryptoCurrency> getCryptoListResponse() {
        List<CryptoCurrency> cryptoCurrencyList = coingeckoService.getCryptoFromApi();
        cryptoCurrencyService.setCryptocurrencyAttributes(cryptoCurrencyList);
        return cryptoCurrencyList;
    }

    public void sendInfoFromCryptoList(CryptoRequest request) {
        try {
            this.symbolToShare = request.getSymbol();
            log.info("Symbol received by sendInfoFromCryptoList(), symbolToShare setting");
        } catch (NullPointerException e) {
            log.warn("CryptoRequest: symbol value is null: sendInfoFromCryptoListResponse()");
            throw new NullPointerException();
        }
    }

    public String getSymbolToShare() {
        return symbolToShare;
    }


    public List<Investment> getSortedInvestmentsResponse() {
        List<Investment> sortedinvestments = investmentService.getAllInvestmentsSortedByDate(userService.getUserById(1L));
        if (sortedinvestments.isEmpty()) {
            log.warn("Response List is empty, getSortedInvestmentsResponse()");
            return sortedinvestments;
        }
        log.info("Outgoing sortedinvestments from: getSortedInvestmentsResponse()");
        return sortedinvestments;
    }

    public CryptoCurrency saveInvestmentPageView() {
        String symbol = getSymbolToShare();
        if (symbol == null) {
            symbol = "btc";
        }
        try {
            return cryptoCurrencyService.getCryptoCurrencyForInvestment(symbol);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Investment processSaveInvestment(InvestmentRequest request) {
        String symbol = getSymbolToShare();
        return investmentService.setInvestmentParamsAndSave(request, symbol);
    }

}
