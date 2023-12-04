package pl.stepien.investmentappangular.model.exception.customExceptions;

public class TooManyRequestsException extends RuntimeException{
    public TooManyRequestsException(){
        super("You have reached entry limit on Coingecko api");
    }
}
