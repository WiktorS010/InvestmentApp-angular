package pl.stepien.investmentappangular.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.controller.CryptoController;
import pl.stepien.investmentappangular.model.exception.customExceptions.InternalServerErrorException;
import pl.stepien.investmentappangular.model.exception.customExceptions.InvalidRequestException;
import pl.stepien.investmentappangular.model.exception.customExceptions.NullCryptoRequestSymbolException;
import pl.stepien.investmentappangular.model.request.CryptoRequest;

@Service
@RequiredArgsConstructor
public class CryptoRequestService {
    private static final Logger log = LogManager.getLogger(CryptoController.class);

    public void checkCryptoRequestSymbolValue(CryptoRequest request) {
        try {
            if (request == null) {
                log.warn("CryptoRequest is null in checkCryptoRequestSymbolValue()");
                throw new InvalidRequestException();
            } else if (request.getSymbol() == null){
                log.warn("CryptoRequest symbol value is null, checkCryptoRequestSymbolValue()");
                throw new NullCryptoRequestSymbolException();
            }
        } catch (Exception e) {
            log.error("An unexpected error occurred in sendInfoFromCryptoList()");
            throw new InternalServerErrorException();
        }
    }

}