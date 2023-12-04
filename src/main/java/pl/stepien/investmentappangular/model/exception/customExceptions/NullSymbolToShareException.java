package pl.stepien.investmentappangular.model.exception.customExceptions;

public class NullSymbolToShareException extends RuntimeException{
    public NullSymbolToShareException(){
        super("Symbol to share with other methods is null");
    }
}
