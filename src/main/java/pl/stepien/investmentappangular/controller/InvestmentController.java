package pl.stepien.investmentappangular.controller;


import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.stepien.investmentappangular.model.request.InvestmentRequest;
import pl.stepien.investmentappangular.model.entity.CryptoCurrency;
import pl.stepien.investmentappangular.model.entity.Investment;
import pl.stepien.investmentappangular.service.InvestmentService;
import pl.stepien.investmentappangular.service.RestFacadeService;


import java.util.List;


@RestController
@RequestMapping("/investment")
@RequiredArgsConstructor
public class InvestmentController {
    private static final Logger log = LogManager.getLogger(InvestmentController.class);
    private final InvestmentService investmentService;
    private final RestFacadeService restFacadeService;

    @GetMapping("/list")
    public ResponseEntity<List<Investment>> getSortedInvestments() {
        return ResponseEntity.ok(restFacadeService.getSortedInvestmentsResponse());
    }

    @GetMapping("/cryptocurrency")
    public ResponseEntity<CryptoCurrency> saveInvestmentPage() {
        return ResponseEntity.ok(restFacadeService.saveInvestmentPageView());
    }

    @PostMapping("/save")
    public ResponseEntity<Investment> saveInvestment(@RequestBody InvestmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restFacadeService.processSaveInvestment(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestment(@PathVariable("id") Long id) {
        return ResponseEntity.ok(restFacadeService.getInvestmentIdView(id));
    }

    @DeleteMapping("/{id}")
    public void deleteInvestment(@PathVariable("id") Long id) {
        investmentService.deleteInvestment(id);
    }
}
