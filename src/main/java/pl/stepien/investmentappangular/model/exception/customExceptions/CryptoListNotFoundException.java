package pl.stepien.investmentappangular.model.exception.customExceptions;

public class CryptoListNotFoundException extends RuntimeException {
    public CryptoListNotFoundException() {
        super("Could not load Cryptocurrency list");
    }
}
