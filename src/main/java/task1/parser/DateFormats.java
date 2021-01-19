package task1.parser;

import java.time.format.DateTimeFormatter;

public enum DateFormats {
    slashed(DateTimeFormatter.ofPattern("yyyy/MM/dd")),
    noSeparator(DateTimeFormatter.ofPattern("ddMMyyyy'T'HHmm")),
    standard(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    private final DateTimeFormatter value;

    DateFormats(DateTimeFormatter value) {
        this.value = value;
    }

    public DateTimeFormatter getValue() {
        return value;
    }
}
