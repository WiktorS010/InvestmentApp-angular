package pl.stepien.investmentappangular.service;

import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.model.entity.User;
import pl.stepien.investmentappangular.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }


}
