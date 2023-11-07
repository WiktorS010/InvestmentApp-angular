package pl.stepien.investmentappangular.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.stepien.investmentappangular.coingeckoApiConnection.CoingeckoApiConnection;
import pl.stepien.investmentappangular.model.CryptoCurrency;
import pl.stepien.investmentappangular.model.Investment;
import pl.stepien.investmentappangular.service.CryptoCurrencyService;
import pl.stepien.investmentappangular.service.InvestmentService;
import pl.stepien.investmentappangular.service.UserService;


import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/investment")
@RequiredArgsConstructor
public class InvestmentController {

    //Tutaj moglbys zastosowac wzorzec projektowy Facade/Fasada - poczytaj co to za wzorzec lub obejrzyj jakis filmik
    //Wtedy idealnie mialbys tu jeden service - fasadowy - ktory wewnwtrz ma wszystkie te inne serwisy
    //kontroller jest prosty i przejrzysty -> a cala logika biznesowa schowana jest w innej klasie

    private final InvestmentService investmentService;
    private final UserService userService;
    private final CoingeckoApiConnection coingeckoApiConnection;
    private final CryptoCurrencyService cryptoCurrencyService;
    private ObjectMapper objectMapper;

    //Podobna sytuacja, nalezy wziac pod uwage rozne sytuacje - nie zawsze ok
    @GetMapping("/list")
    public ResponseEntity<List<Investment>> getSortedInvestments() {
        //na to co w nawiasie moglbys miec metode w fasadzie
        return ResponseEntity.ok(investmentService.getAllInvestmentsSortedByIncome(userService.getUserById(1L)));
    }

    //Dlaczego to jest save? metoda na podstawie argumentu zwraca obieky cryptoCurrency. Where jakis zapis?
    @GetMapping("/save")
    public ResponseEntity<CryptoCurrency> saveInvestmentPage(HttpSession session) {
        String symbol = (String) session.getAttribute("symbol");

        //loguje, nie wypisuj
        System.out.println(symbol);

        //warto sprawdzic czy aby lista nie jest pusta
        List<CryptoCurrency> cryptoList = coingeckoApiConnection.getCrypto();
        CryptoCurrency cryptoCurrency = cryptoCurrencyService.findCryptoBySymbol(cryptoList, symbol.toUpperCase());
        return ResponseEntity.ok(cryptoCurrency);
    }

    //Zrob jakis model requestu i nim sie posluguj
    @PostMapping("/save")
    public ResponseEntity<Investment> saveInvestment(@RequestBody Map<String, Object> request, HttpSession session) {
        objectMapper.findAndRegisterModules();
        String symbol = (String) session.getAttribute("symbol");
        Investment investment = objectMapper.convertValue(request.get("investment"), Investment.class);

        List<CryptoCurrency> cryptoList = coingeckoApiConnection.getCrypto();
        CryptoCurrency cryptoCurrency = cryptoCurrencyService.findCryptoBySymbol(cryptoList, symbol.toUpperCase());
        System.out.println(cryptoCurrency);
        investment.setEnterPrice(cryptoCurrency.getPrice());

        //Cala ta logika biznesowa najlepiej by bylo zeby trafila do jakiegos serwisu, a nie zalatwiona w kontrollerze
        System.out.println(investment);
        Investment createdInvestment = investmentService.addInvestment(investment);
        return ResponseEntity.ok(createdInvestment);
    }


    //Poczytaj/obejrzyj o samym koncepcie REST API - kontroller zawsze powinien dotyczyc konkretnego zasobu - np cars,
    //dzieki metodom - jestes w stanie pobierac, zmieniac, usuwac dane zasoby. Nie potrzebujemy w sciezce dodawac GET/DELETE
    //To powinno byc opisane w necie - to moze byc pytanie na rozmowie.
    //Co wiecej polecam poczytac o HTTP/HTTPS i znac ze 4/5 metod jakie ma ten protokol
    @GetMapping("/get/{id}")
    public ResponseEntity<Investment> getInvestment(@PathVariable("id") Long id) {
        return ResponseEntity.ok(investmentService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInvestment(@PathVariable("id") Long id) {
        investmentService.deleteInvestment(id);
    }
}
