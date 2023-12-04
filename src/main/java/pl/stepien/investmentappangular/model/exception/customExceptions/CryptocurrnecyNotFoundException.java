package pl.stepien.investmentappangular.model.exception.customExceptions;

public class CryptocurrnecyNotFoundException extends RuntimeException {
    public CryptocurrnecyNotFoundException(String symbol) {
        super("Could not find Cryptocurrency with symbol: " + symbol);
    }
}
