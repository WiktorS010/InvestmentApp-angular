package pl.stepien.investmentappangular.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class DateService {
    public ZonedDateTime setCurrentDate(){
        Instant now = Instant.now();
        return ZonedDateTime.ofInstant(now, ZoneId.systemDefault());
    }
}
