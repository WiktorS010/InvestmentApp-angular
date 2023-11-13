package pl.stepien.investmentappangular.coingeckoApiConnection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.stepien.investmentappangular.model.CryptoCurrency;

import java.util.List;

class coingeckoApiConnectionTest {

    //Poczytaj o WireMocku, na pewno sa filmiki i zrob sobie test mockujac endpoint i to co zwraca
    //Takie cos w portfolio bedzie mialo spora wartosc.
    @Test
    void testGetList() {
        CoingeckoApiConnection coingeckoApiConnection = new CoingeckoApiConnection();
        List<CryptoCurrency> resultList = coingeckoApiConnection.getCrypto();
        assert (resultList != null);
    }

}