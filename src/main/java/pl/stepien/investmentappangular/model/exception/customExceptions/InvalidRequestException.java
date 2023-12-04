package pl.stepien.investmentappangular.model.exception.customExceptions;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException() {
        super("Invalid data format, please check your request.");
    }
}
