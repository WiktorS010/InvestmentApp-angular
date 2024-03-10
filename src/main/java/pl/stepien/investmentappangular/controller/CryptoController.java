package pl.stepien.investmentappangular.controller;


import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.stepien.investmentappangular.model.request.CryptoRequest;
import pl.stepien.investmentappangular.service.*;
import pl.stepien.investmentappangular.model.entity.CryptoCurrency;

import java.util.List;


@RestController
@RequestMapping("/cryptocurrency")
@RequiredArgsConstructor
public class CryptoController {
    private static final Logger log = LogManager.getLogger(CryptoController.class);
    private final RestFacadeService restFacadeService;

    @GetMapping("/list")
    public ResponseEntity<List<CryptoCurrency>> showCryptoList() {
        return ResponseEntity.ok(restFacadeService.getCryptoListResponse());
    }

    @PostMapping("/symbol")
    public ResponseEntity<Void> receiveInfoFromCryptoList(@RequestBody CryptoRequest request) {
        log.info("Incoming request Cryptocurrency symbol: {}", request.getSymbol() + ", receiveInfoFromCryptoList()");
        restFacadeService.sendInfoFromCryptoList(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
