package pl.stepien.investmentappangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.stepien.investmentappangular.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
}
