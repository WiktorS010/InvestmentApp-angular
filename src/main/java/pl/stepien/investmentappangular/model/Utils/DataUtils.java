package pl.stepien.investmentappangular.model.Utils;

import java.text.NumberFormat;
import java.util.Locale;

public class DataUtils {
    private static final NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);

    public static String formatNumberWithCommas(Object number) {
        return numberFormat.format(number);
    }
}
