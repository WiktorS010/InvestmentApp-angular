package pl.stepien.investmentappangular.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.stepien.investmentappangular.service.InvestmentService;
import pl.stepien.investmentappangular.service.UserService;


@RestController
@RequestMapping("/user/investment")
@RequiredArgsConstructor
public class InvestmentController {
    private final UserService userService;
    private final InvestmentService investmentService;

}
