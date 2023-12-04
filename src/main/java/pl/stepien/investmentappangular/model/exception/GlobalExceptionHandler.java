package pl.stepien.investmentappangular.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.stepien.investmentappangular.model.exception.customExceptions.*;
import pl.stepien.investmentappangular.model.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvestmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInvestmentNotFoundException(InvestmentNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvestmentListNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInvestmentListNotFoundException(InvestmentListNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CryptocurrnecyNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCryptocurrencyNotFoundException(CryptocurrnecyNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CryptoListNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCryptoListNotFoundException(CryptoListNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRequestException(InvalidRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<ErrorResponse> handleInternalServerException(InternalServerErrorException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(TooManyRequestsException.class)
    public ResponseEntity<ErrorResponse> handleTooManyRequestsException(TooManyRequestsException exception) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.TOO_MANY_REQUESTS.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponse);
    }
    @ExceptionHandler(NullCryptoRequestSymbolException.class)
    public ResponseEntity<ErrorResponse> handleNullCryptoRequestSymbolException(NullCryptoRequestSymbolException exception){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }
    @ExceptionHandler(NullSymbolToShareException.class)
    public ResponseEntity<ErrorResponse> handleNullSymbolToShareException(NullSymbolToShareException exception){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NO_CONTENT.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
    }
}
