package task3;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

public class Printer {
    private static final String RESULT_PATTERN = "%s: %s Wind:%s Solar:%s Others:%s\n";
    private static final String CSS_TABLE_CELLS_ACTUAL_QUERY = "td:not(td:nth-of-type(5)):not(td:first-of-type)";
    private static final String CSS_TABLE_CELLS_FORECAST_QUERY = "td:not(td:nth-of-type(4))";
    private static final Logger LOGGER = LogManager.getLogger(Printer.class);


    static void printSingleRow(int hour, String timeDescription, Elements cells) {


        List<String> cellsText = cells.stream().map(Element::text).collect(Collectors.toList());
        String formattedHour = String.format("%02d", hour);
        String wind = cellsText.get(0);
        String solar = cellsText.get(1);
        String others = cellsText.get(2);
        System.out.printf(RESULT_PATTERN, timeDescription, formattedHour, wind, solar, others);
    }

    void printHorizontalLine(String title) {
        LOGGER.info("--------------------------------" + title + "--------------------------------");
    }

    private static void printTableResults(Elements rowsToPrint, String cssQuery) {
        for (int i = 0; i < rowsToPrint.size(); i++) {
            String timeDescription = "Actual";
            Elements tableCells = rowsToPrint.get(i).select(cssQuery + ":not(.txtPREV)");
            if (tableCells.size() <= 1) {
                timeDescription = "Forecast";
                tableCells = rowsToPrint.get(i).select(cssQuery);
            }
            if (!tableCells.isEmpty()) {
                printSingleRow(i, timeDescription, tableCells);
            }
        }
    }

    void printActualAndForecast(Pair<Elements, Elements> powerGenerationData) {

        printHorizontalLine("Today");
        printTableResults(powerGenerationData.getLeft(), CSS_TABLE_CELLS_ACTUAL_QUERY);
        printHorizontalLine("Tomorrow");
        printTableResults(powerGenerationData.getRight(), CSS_TABLE_CELLS_FORECAST_QUERY);
    }
}
