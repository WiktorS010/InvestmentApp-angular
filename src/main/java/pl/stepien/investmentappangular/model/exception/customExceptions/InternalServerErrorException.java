package pl.stepien.investmentappangular.model.exception.customExceptions;

public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException(){
        super("Internal server error. Please contact support for assistance.");
    }
}
