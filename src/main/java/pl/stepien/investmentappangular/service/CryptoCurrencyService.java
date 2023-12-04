package pl.stepien.investmentappangular.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.controller.CryptoController;
import pl.stepien.investmentappangular.model.Utils.DataUtils;
import pl.stepien.investmentappangular.model.entity.CryptoCurrency;
import pl.stepien.investmentappangular.model.exception.customExceptions.CryptocurrnecyNotFoundException;
import pl.stepien.investmentappangular.model.exception.customExceptions.InternalServerErrorException;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CryptoCurrencyService {

    private static final Logger log = LogManager.getLogger(CryptoController.class);
    private final CoingeckoService coingeckoService;

    public CryptoCurrency getCryptoCurrencyForInvestment(String symbol) {
        try {
            List<CryptoCurrency> cryptoList = coingeckoService.getCryptoFromApi();
            Optional<CryptoCurrency> optionalCryptoCurrency = findCryptoBySymbol(cryptoList, symbol);
            return optionalCryptoCurrency.orElseThrow(() -> {
                log.error("Crypto currency with symbol : {} not found", symbol);
                return new CryptocurrnecyNotFoundException(symbol);
            });
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }
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
