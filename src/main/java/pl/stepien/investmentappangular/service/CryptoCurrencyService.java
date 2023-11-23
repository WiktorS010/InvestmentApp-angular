package pl.stepien.investmentappangular.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.controller.CryptoController;
import pl.stepien.investmentappangular.model.Utils.DataUtils;
import pl.stepien.investmentappangular.model.entity.CryptoCurrency;
import pl.stepien.investmentappangular.model.request.CryptoRequest;
import pl.stepien.investmentappangular.model.response.CryptoResponse;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CryptoCurrencyService {

    private static final Logger log = LogManager.getLogger(CryptoController.class);
    private final CoingeckoService coingeckoService;

    public CryptoCurrency getCryptoCurrencyForInvestment(String symbol) {
        List<CryptoCurrency> cryptoList = coingeckoService.getCryptoFromApi();
        Optional<CryptoCurrency> optionalCryptoCurrency = findCryptoBySymbol(cryptoList, symbol);
        optionalCryptoCurrency.ifPresent(cryptoCurrency -> {
            log.info("Crypto currency with symbol : {}, outgoing from getCryptoCurrencyForInvestment()", symbol);
        });
        return optionalCryptoCurrency.orElse(null);
    }

    public Optional<CryptoCurrency> findCryptoBySymbol(List<CryptoCurrency> cryptoList, String symbol) {
        for (CryptoCurrency cryptoCurrency : cryptoList) {
            if (cryptoCurrency.getSymbol().equals(symbol)) {
                return Optional.of(cryptoCurrency);
            }
        }
        log.warn("Crypto currency with symbol {} not found, findCryptoBySymbol()", symbol);
        return Optional.empty();
    }


    public void setCryptocurrencyAttributes(List<CryptoCurrency> cryptoCurrencyList) {
        for (CryptoCurrency cryptoCurrency : cryptoCurrencyList) {
            String formattedMarketCap = DataUtils.formatNumberWithCommas(cryptoCurrency.getMarketCap());
            String formattedPrice = DataUtils.formatNumberWithCommas(cryptoCurrency.getPrice());
            cryptoCurrency.setMarketCapString(formattedMarketCap);
            cryptoCurrency.setPriceString(formattedPrice);
            cryptoCurrency.setSymbol(cryptoCurrency.getSymbol().toUpperCase());
            cryptoCurrency.setDayChange(Math.round(cryptoCurrency.getDayChange() * 100.0) / 100.0);
        }
    }

}
