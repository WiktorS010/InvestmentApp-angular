package pl.stepien.investmentappangular.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.controller.InvestmentController;
import pl.stepien.investmentappangular.model.entity.User;
import pl.stepien.investmentappangular.model.exception.customExceptions.InternalServerErrorException;
import pl.stepien.investmentappangular.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final Logger log = LogManager.getLogger(InvestmentController.class);
    private final UserRepository userRepository;


    public User getUserById(Long id) {
        try {
            User user = userRepository.findUserById(id);
            if (user == null) {
                log.warn("Could not find user with id: {}", id);
                throw new EntityNotFoundException("User not found with id: " + id);
            }
            return user;
        } catch (Exception e) {
            throw new InternalServerErrorException();
        }

    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
