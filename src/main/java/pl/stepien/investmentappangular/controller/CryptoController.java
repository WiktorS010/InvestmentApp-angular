package pl.stepien.investmentappangular.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.stepien.investmentappangular.coingeckoApiConnection.CoingeckoApiConnection;

import java.util.List;

@RestController
@RequestMapping("/cryptocurrency")
public class CryptoController {
    @Autowired
    private CoingeckoApiConnection coingeckoApiConnection;

    @GetMapping("/list")
    public ResponseEntity<List<?>> showCryptoList() {
        return ResponseEntity.ok(coingeckoApiConnection.getCrypto(PageRequest.of(10, 20)));
    }

}
