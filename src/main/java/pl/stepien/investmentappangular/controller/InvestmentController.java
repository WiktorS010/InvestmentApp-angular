package pl.stepien.investmentappangular.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.stepien.investmentappangular.model.Investment;
import pl.stepien.investmentappangular.repository.CryptoCurrencyRepository;
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
    private final CryptoCurrencyRepository cryptoCurrencyRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Investment>> getSortedInvestments() {
        //TODO download current user from list or smth.
        return ResponseEntity.ok(investmentService.getAllInvestmentsSortedByIncome(userService.getUserById(1L)));
    }
    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/save")
    public ResponseEntity<Investment> saveInvestment(@RequestBody Map<String, Object> request) {
        objectMapper.findAndRegisterModules();
        String symbol = (String) request.get("symbol");
        Investment investment = objectMapper.convertValue(request.get("investment"), Investment.class);
        System.out.println(investment);
        System.out.println(symbol);
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
