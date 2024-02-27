package pl.stepien.investmentappangular.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.stepien.investmentappangular.model.entity.User;
import pl.stepien.investmentappangular.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    //System under test
    private UserService userService;

    // Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp () {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    void find() throws Exception {
        User excepcted = new User();

    }
}