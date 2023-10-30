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


    private String formatNumberWithCommas(Long number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return numberFormat.format(number);
    }
    private String formatNumberWithCommas(Double number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        return numberFormat.format(number);
    }

}
