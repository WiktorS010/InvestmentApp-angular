package pl.stepien.investmentappangular.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.stepien.investmentappangular.model.entity.User;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class UserRepositoryMockTest {

    @Mock
    UserRepository userRepository;

    @Test
    void testFindUserById() {

        Long id = 1L;
        User expectedUser = new User();

        when(userRepository.findUserById(id)).thenReturn(expectedUser);

        User resultUser = userRepository.findUserById(id);

        assertEquals(expectedUser, resultUser);

        verify(userRepository).findUserById(id);


    }

}
