package pl.stepien.investmentappangular.model.exception.customExceptions;

public class NullCryptoRequestSymbolException extends RuntimeException{
    public NullCryptoRequestSymbolException(){
        super("Crypto Request attribute symbol is null ");
    }
}
