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
    private final InvestmentService investmentService;
    private final UserService userService;
    private final CoingeckoApiConnection coingeckoApiConnection;
    private final CryptoCurrencyService cryptoCurrencyService;
    private ObjectMapper objectMapper;

    @GetMapping("/list")
    public ResponseEntity<List<Investment>> getSortedInvestments() {
        return ResponseEntity.ok(investmentService.getAllInvestmentsSortedByIncome(userService.getUserById(1L)));
    }

    @GetMapping("/save")
    public ResponseEntity<CryptoCurrency> saveInvestmentPage(HttpSession session) {
        String symbol = (String) session.getAttribute("symbol");

        System.out.println(symbol);

        List<CryptoCurrency> cryptoList = coingeckoApiConnection.getCrypto();
        CryptoCurrency cryptoCurrency = cryptoCurrencyService.findCryptoBySymbol(cryptoList, symbol.toUpperCase());
        return ResponseEntity.ok(cryptoCurrency);
    }

    @PostMapping("/save")
    public ResponseEntity<Investment> saveInvestment(@RequestBody Map<String, Object> request,HttpSession session) {
        objectMapper.findAndRegisterModules();
        String symbol = (String) session.getAttribute("symbol");
        Investment investment = objectMapper.convertValue(request.get("investment"), Investment.class);

        List<CryptoCurrency> cryptoList = coingeckoApiConnection.getCrypto();
        CryptoCurrency cryptoCurrency = cryptoCurrencyService.findCryptoBySymbol(cryptoList, symbol.toUpperCase());
        System.out.println(cryptoCurrency);
        investment.setEnterPrice(cryptoCurrency.getPrice());

        System.out.println(investment);
        Investment createdInvestment = investmentService.addInvestment(investment);
        return ResponseEntity.ok(createdInvestment);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Investment> getInvestment(@PathVariable("id") Long id) {
        return ResponseEntity.ok(investmentService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteInvestment(@PathVariable("id") Long id) {
        investmentService.deleteInvestment(id);
    }
}
