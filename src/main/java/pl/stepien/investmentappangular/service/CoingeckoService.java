package pl.stepien.investmentappangular.service;


import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import pl.stepien.investmentappangular.model.entity.CryptoCurrency;


import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CoingeckoService {
    private static final String CRYPTO_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20";
    private static final Logger log = LogManager.getLogger(CoingeckoService.class);
    private final RestTemplate restTemplate = new RestTemplate();


    public List<CryptoCurrency> getCryptoFromApi() {
        ResponseEntity<List<CryptoCurrency>> response = getExchange();
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("List of cryptocurrency downloaded from coingecko api, getCryptoFromApi()");
            return response.getBody();
        } else
            log.warn("Failed to fetch cryptocurrency data, getCryptoFromApi(). Return: empty list");
        return List.of();
    }

    private ResponseEntity<List<CryptoCurrency>> getExchange() {
        try {
            return restTemplate.exchange(CRYPTO_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<CryptoCurrency>>() {
            });
        } catch (HttpClientErrorException.TooManyRequests e) {
            log.warn("TooManyRequests in coingecko Api, getExchange()", e);
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(Collections.emptyList());
        } catch (RestClientException e) {
            log.warn("RestClientException, getExchange()", e);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        }
    }
}
