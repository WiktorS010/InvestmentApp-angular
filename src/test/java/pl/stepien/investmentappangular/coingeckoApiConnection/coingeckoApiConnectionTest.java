package pl.stepien.investmentappangular.coingeckoApiConnection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.stepien.investmentappangular.model.CryptoCurrency;

import java.util.List;

class coingeckoApiConnectionTest {
    @Test
    void testGetList() {
        CoingeckoApiConnection coingeckoApiConnection = new CoingeckoApiConnection();
        List<CryptoCurrency> resultList = coingeckoApiConnection.getCrypto();
        assert (resultList != null);
    }

}