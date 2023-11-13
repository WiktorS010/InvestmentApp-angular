package pl.stepien.investmentappangular.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.Locale;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = "id", ignoreUnknown = true)
public class CryptoCurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String symbol;
    @JsonProperty("market_cap_rank")
    private Long marketCapRank;
    @JsonProperty("current_price")
    private Double price;
    @JsonProperty("current_price_string")
    private String priceString;
    @JsonProperty("price_change_percentage_24h")
    private Double dayChange;
    @JsonProperty("market_cap")
    private Long marketCap;
    @JsonProperty("market_cap_string")
    private String marketCapString;
    private String image;
    @OneToOne
    private Investment investment;

    public void setSymbol(String symbol) {
        this.symbol = symbol.toUpperCase();
    }

    //Nie wiem co tu sie wyprawia - zapoznaj sie z klasami Instant - kozacka klasa lub LocalDateTime
    public void setDayChange(Double dayChange) {
        this.dayChange = Math.round(dayChange * 100.0) / 100.0;
    }

    public String getMarketCapString() {
        return formatNumberWithCommas(marketCap);
    }

    public void setMarketCapString(String marketCapString) {
        this.marketCapString = marketCapString;
    }

    public String getPriceString(){
        return formatNumberWithCommas(price);
    }
    public void setPriceString(String priceString){
        this.priceString = priceString;
    }


    //W obu metodach korzystasz na koniec z metody format klasy String, zauwaz ze metoda ta przyjmuje argument klasy Object
    //Co za tym idzie mozemy miec jedna metode formatNumberWithCommas - przekazac jako argument instancje klasy Object.
    //Te metody nie dotycza bezposrednio klasy CruptoCurrency - zrob np klase DataUtils
    //tam zrob sobie tam static final NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
    //i publiczna statyczna metode formatNumberWithCommas(Object number) {
    //i tego sobie uzyj - najlepiej nie tu w getterze, getter powinien pobrac wartosc jak jest czyli np Long,
    //a jesli potrzebujesz stringa to uzyj tej statycznej metody z utilsow tam gdzie se pobierasz tym gettem
    private String formatNumberWithCommas(Long number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return numberFormat.format(number);
    }
    private String formatNumberWithCommas(Double number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return numberFormat.format(number);
    }

}
