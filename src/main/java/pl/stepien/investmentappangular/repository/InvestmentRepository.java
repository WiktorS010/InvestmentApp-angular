package pl.stepien.investmentappangular.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.stepien.investmentappangular.model.Investment;

import java.util.List;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findAllByUser_Id(Long user_id);
}
