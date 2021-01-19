package task1.parser;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Optional;
import java.util.UnknownFormatConversionException;

public class NumberParser {

    private static final String NUMBER_ERROR_MESSAGE = "Unknown number format: %s is not supported";
    private static final Logger LOGGER = LogManager.getLogger(NumberParser.class);


    static String extractNumberFromString(String rawField) {
        return StringUtils.substringBetween(rawField, "(", ")");
    }

    public static String convertToCorrectNumberFormat(String rawNumber) {
        Optional<String> extractedNumber = Arrays.stream(LocalizedNumberFormats.values())
                .map(numberFormat -> parseNumber(extractNumberFromString(rawNumber), numberFormat.getValue()))
                .filter(Optional::isPresent)
                .map(number -> LocalizedNumberFormats.germanNoGrouping.getValue().format(number.get()))
                .findFirst();
        if (extractedNumber.isPresent()) {
            return extractedNumber.get();
        } else {
            LOGGER.error(String.format(NUMBER_ERROR_MESSAGE, rawNumber));
            throw new UnknownFormatConversionException(String.format(NUMBER_ERROR_MESSAGE, rawNumber));
        }
    }

    private static Optional<Double> parseNumber(String rawNumber, DecimalFormat df) {
        try {
            return Optional.of((double) df.parse(rawNumber));
        } catch (ParseException | ClassCastException exc) {
            return Optional.empty();
        }
    }
}
