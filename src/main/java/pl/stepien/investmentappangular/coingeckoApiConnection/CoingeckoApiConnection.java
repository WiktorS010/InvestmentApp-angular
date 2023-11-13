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

//Sugeruje zrobic z tej klasy service -> CoingeckoService
@Component
public class CoingeckoApiConnection {

    private final RestTemplate restTemplate = new RestTemplate();

    //dodaj static i wynies ponad wszystkie pola. Trzymanie stalych czyli pol static final na samej gorze to dobra praktyka.
    private final String CRYPTO_URL = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20";


    //Tutaj kilka rzecz:
    // 1. Mamy dwie metody obie korzystaja poki co dokladnie z tego samego - sugeruje zatem wydzielic do oddzielej prawtnej funkcji
    //    wyciagniecie danych z coingeco. Zaznacz sobie kursorem od znaku rownosci do }); wlacznie i kliknij ctrl + alt + m
    //    Tak wygenerowana metode mozesz uzyc w obu metodach pubilicznych.
    // 2. Nie wiem jak ma dokladnie dzialac metoda z parametrem - czy zapytanie do coingecko bedzie parametryzowane czy tez bedziesz
    //    przetwarzal pobrane dane z uzyciem obiektu of.
    // 3. Zauwaz ze Coingecko moze zwrocic Ci roznego rodzaju odpowiedzi - niekonicznie zawsze 200 ok, takze zanim pobierzem body i
    //    zwrocisz odpowiedz dalej - zrob jakas validacje - zaloguj rodzaj odpowiedzi i zwroc np pusta liste.
    public List<CryptoCurrency> getCrypto(PageRequest of) {
        ResponseEntity<List<CryptoCurrency>> response =
                restTemplate.exchange(CRYPTO_URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<CryptoCurrency>>() {
                        });
        return response.getBody();
    }


    public List<CryptoCurrency> getCrypto() {
        ResponseEntity<List<CryptoCurrency>> response =
                restTemplate.exchange(CRYPTO_URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<CryptoCurrency>>() {
                        });
        return response.getBody();
    }
}
