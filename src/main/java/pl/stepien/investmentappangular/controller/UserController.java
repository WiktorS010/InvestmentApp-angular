package pl.stepien.investmentappangular.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.stepien.investmentappangular.coingeckoApiConnection.CoingeckoApiConnection;
import pl.stepien.investmentappangular.model.CryptoCurrency;
import pl.stepien.investmentappangular.model.User;
import pl.stepien.investmentappangular.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
   private UserService userService;
   private CoingeckoApiConnection coingeckoApiConnection;

@GetMapping("/home")
    public List<CryptoCurrency> showUserHomePage(Long id, Model model){
        User user = userService.getUserById(id);
        return coingeckoApiConnection.getCrypto();
    }
}
