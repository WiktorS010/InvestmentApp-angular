package pl.stepien.investmentappangular.model.exception.customExceptions;

public class InvestmentNotFoundException extends RuntimeException {
    public InvestmentNotFoundException(Long id) {
        super("Could not find investment: " + id);
    }
}
