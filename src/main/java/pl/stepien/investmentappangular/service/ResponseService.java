package pl.stepien.investmentappangular.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import pl.stepien.investmentappangular.controller.CryptoController;
import pl.stepien.investmentappangular.model.request.CryptoRequest;
import pl.stepien.investmentappangular.model.response.CryptoResponse;

@Service
public class ResponseService {
    private static final Logger log = LogManager.getLogger(CryptoController.class);

    public CryptoResponse setResponseValue(CryptoRequest request) {
        if (request.getSymbol() == null) {
            log.warn("Symbol value of CryptoCurrency is null ! setResponseValue()");
        }
        CryptoResponse response = new CryptoResponse();
        response.setSymbol(request.getSymbol());
        if (response.getSymbol() == null){
            log.warn("Response symbol value is null, setResponseValue()");
        }
        return response;
    }
}
