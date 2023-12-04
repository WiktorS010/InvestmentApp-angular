package pl.stepien.investmentappangular.model.exception.customExceptions;

public class InvestmentListNotFoundException extends RuntimeException{
    public InvestmentListNotFoundException(){
        super("Could not load Investment list");
    }
}
