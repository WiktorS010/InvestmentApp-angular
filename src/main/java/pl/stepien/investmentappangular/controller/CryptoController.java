package pl.stepien.investmentappangular.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.stepien.investmentappangular.coingeckoApiConnection.CoingeckoApiConnection;
import pl.stepien.investmentappangular.model.CryptoCurrency;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/cryptocurrency")
public class CryptoController {

    @Autowired
    private CoingeckoApiConnection coingeckoApiConnection;

    //Czy zawsze dostaniesz liste i zawsze powinines zwracac response ok?
    @GetMapping("/list")
    public ResponseEntity<List<CryptoCurrency>> showCryptoList() {
        return ResponseEntity.ok(coingeckoApiConnection.getCrypto(PageRequest.of(1, 20)));
    }

    //To chyba niegotowe :D
    //Zrob jakis obiekt requestu zamiast mapy mordo
    @PostMapping("/list")
    public ResponseEntity<Map<String, Object>> sendInfoFromCryptoList(@RequestBody Map<String, Object> request, HttpSession session) {
        String symbol = (String) request.get("symbol");
        Map<String, Object> response = new HashMap<>();
        response.put("symbol", symbol);
        session.setAttribute("symbol", symbol);
        return ResponseEntity.ok(response);
    }
}
