package task1.printer;

import task1.parser.DateParser;
import task1.parser.NumberParser;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;

import java.util.List;
import java.util.stream.Collectors;

public class Printer {
    private static final String BRACKET_NUMBER_REGEX = " ?\\(.*\\)";
    private static final Logger LOGGER = LogManager.getLogger(Printer.class);

    private final DateParser dateParser;
    private final NumberParser numberParser;

    public Printer(DateParser dateParser, NumberParser numberParser) {
        this.dateParser = dateParser;
        this.numberParser = numberParser;
    }

    private static class RowBuilder {
        static final String SEPARATOR = "    ";
        private final StringBuilder stringBuilder = new StringBuilder();

        RowBuilder appendAndSeparate(String textToAppend) {
            stringBuilder.append(textToAppend).append(SEPARATOR);
            return this;
        }

        String text() {
            return stringBuilder.toString();
        }
    }

    private static String formatGenerationField(String rawGenerationField, String formattedNumber) {
        String text = StringUtils.substringBefore(rawGenerationField, "(");
        return String.format("%s (%s)", text, formattedNumber);
    }

    private static List<String> prepareSingleRow(Element words) {
        return words
                .select("th")
                .stream()
                .map(element -> element.text().toUpperCase())
                .collect(Collectors.toList());
    }

    public void printSingleRow(Element words) {
        List<String> singleRow = prepareSingleRow(words);
        String localDate = DateParser.extractDateFromString(singleRow.get(0));
        String formattedNumber = NumberParser.convertToCorrectNumberFormat(singleRow.get(2));

        RowBuilder rowBuilder = new RowBuilder()
                .appendAndSeparate(localDate)
                .appendAndSeparate(singleRow.get(1))
                .appendAndSeparate(formatGenerationField(singleRow.get(2), formattedNumber))
                .appendAndSeparate(singleRow.get(3))
                .appendAndSeparate(singleRow.get(4).replaceAll("%", " "))
                .appendAndSeparate(singleRow.get(5).replace("+", "Y").replaceAll(BRACKET_NUMBER_REGEX, ""))
                .appendAndSeparate(singleRow.get(6).replaceAll(BRACKET_NUMBER_REGEX, ""))
                .appendAndSeparate(singleRow.get(7).replaceAll(BRACKET_NUMBER_REGEX, "").replaceAll(" ", "."));
        LOGGER.info(rowBuilder.text());
    }
}
