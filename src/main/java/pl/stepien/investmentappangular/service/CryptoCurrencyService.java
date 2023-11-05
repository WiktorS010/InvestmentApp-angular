package pl.stepien.investmentappangular.service;

import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.model.CryptoCurrency;

import java.util.List;

@Service
public class CryptoCurrencyService {

    public CryptoCurrency findCryptoBySymbol(List<CryptoCurrency> cryptoList, String symbol){
        for (CryptoCurrency cryptoCurrency : cryptoList){
            if(cryptoCurrency.getSymbol().equals(symbol)){
                return cryptoCurrency;
            }
        }
        return null;
    }

}
