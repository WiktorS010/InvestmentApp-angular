package pl.stepien.investmentappangular.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.stepien.investmentappangular.model.Investment;
import pl.stepien.investmentappangular.model.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class InvestmentServiceTest {
    @Autowired
    private InvestmentService investmentService;
    @Autowired
    private UserService userService;

    @Test
    public void testGetAllInvestmentsSortedByIncome() {
        User testUser = userService.getUserById(1L);
        List<Investment> sortedList = investmentService.getAllInvestmentsSortedByIncome(testUser);

        List<Investment> listToCompare = new ArrayList<>();
        listToCompare.add(new Investment(2L, 1000.0, testUser));
        listToCompare.add(new Investment(5L, 2000.0, testUser));
        listToCompare.add(new Investment(4L, 3000.0, testUser));
        for (int i = 0; i < sortedList.toArray().length; i++) {
            System.out.println(sortedList.toArray()[i]);
        }
        Assertions.assertArrayEquals(listToCompare.toArray(), sortedList.toArray());
    }
}
