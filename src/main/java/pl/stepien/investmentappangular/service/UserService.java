package pl.stepien.investmentappangular.service;

import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.model.User;
import pl.stepien.investmentappangular.repository.UserRepository;


//BTW: nie ma tu jakiegos pom.xml? Jakiegos gradle'a? Zarządzania dependencjami?
//Zapoznaj sie z tym: https://www.baeldung.com/exception-handling-for-rest-with-spring
//Zrob pakiet exception i dodaj jakies customowe Excepiony -> chocby dziedziczace po RuntimeExceptionie
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
