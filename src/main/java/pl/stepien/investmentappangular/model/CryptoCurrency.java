package pl.stepien.investmentappangular.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonProperty("price_change_percentage_24h")
    private Double dayChange;
    @JsonProperty("market_cap")
    private Long marketCap;
    private String image;
    @OneToOne
    private Investment investment;
}
