package pl.stepien.investmentappangular.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.stepien.investmentappangular.model.entity.Investment;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class InvestmentRepositoryMockTest {

    @Mock
    InvestmentRepository investmentRepository;

    @Test
    void testFindAllByUserId() {
        // Arrange
        Long userId = 1L;
        List<Investment> expectedInvestments = Arrays.asList(new Investment(), new Investment());

        // Mock the repository behavior
        when(investmentRepository.findAllByUser_Id(userId)).thenReturn(expectedInvestments);

        // Act
        List<Investment> result = investmentRepository.findAllByUser_Id(userId);

        //Assert
        assertEquals(expectedInvestments, result);

        //Verify that the repository method was called with the correct parameter
        verify(investmentRepository).findAllByUser_Id(userId);
    }
}

