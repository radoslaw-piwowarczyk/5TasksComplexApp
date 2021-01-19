package task1.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UnknownFormatConversionException;

public class DateParser {
    private static final String DATE_ERROR_MESSAGE = "Unknown date format: %s is not supported";
    private static final Logger LOGGER = LogManager.getLogger(DateParser.class);


    public static String extractDateFromString(String rawDate) {
        Optional<String> extractedDate = Arrays.stream(DateFormats.values())
                .map(df -> parseDate(rawDate, df))
                .dropWhile(Optional::isEmpty)
                .map(date -> date.get().format(DateFormats.standard.getValue()))
                .findFirst();
        if (extractedDate.isPresent()) {
            return extractedDate.get();
        } else {
            LOGGER.error(String.format(DATE_ERROR_MESSAGE, rawDate));
            throw new UnknownFormatConversionException(String.format(DATE_ERROR_MESSAGE, rawDate));
        }
    }

    private static Optional<LocalDate> parseDate(String rawDate, DateFormats df) {
        try {
            return Optional.of(LocalDate.parse(rawDate, df.getValue()));
        } catch (DateTimeParseException parseException) {
        }
        return Optional.empty();
    }
}
