package pl.stepien.investmentappangular.coingeckoApiConnection;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.stepien.investmentappangular.model.CryptoCurrency;

import java.util.List;

@Component
public class CoingeckoApiConnection {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String CRYPTO_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20";

    public List<CryptoCurrency> getCrypto(PageRequest of) {
        ResponseEntity<List<CryptoCurrency>> response =
                restTemplate.exchange(CRYPTO_URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<CryptoCurrency>>() {
                        });
        return response.getBody();
    }
}
