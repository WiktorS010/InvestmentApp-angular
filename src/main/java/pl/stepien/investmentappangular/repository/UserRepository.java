package pl.stepien.investmentappangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.stepien.investmentappangular.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
