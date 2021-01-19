package task1.parser;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public enum LocalizedNumberFormats {
    englishWithGrouping(new DecimalFormat("###,###.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH))),
    getEnglishNoGrouping(new DecimalFormat("###.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH))),
    germanNoGrouping(new DecimalFormat("###.00", DecimalFormatSymbols.getInstance(Locale.GERMAN)));

    private final DecimalFormat value;

    LocalizedNumberFormats(DecimalFormat value) {
        this.value = value;
    }

    public DecimalFormat getValue() {
        return value;
    }
}
