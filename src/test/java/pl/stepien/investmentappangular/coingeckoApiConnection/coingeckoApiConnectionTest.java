package pl.stepien.investmentappangular.coingeckoApiConnection;

import org.junit.Test;
import pl.stepien.investmentappangular.model.CryptoCurrency;

import java.util.List;

public class coingeckoApiConnectionTest {
    @Test
    public void testGetList() {
        coingeckoApiConnection coingeckoApiConnection = new coingeckoApiConnection();
        List<CryptoCurrency> resultList = coingeckoApiConnection.getCrypto();
        assert (resultList != null);
    }
}
