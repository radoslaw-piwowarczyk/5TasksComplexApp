package task3;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Optional;

public class ForecastProcessor {
    private static final Logger LOGGER = LogManager.getLogger(ForecastProcessor.class);

    private static final String DOWNLOAD_ERROR_MESSAGE = "Cannot download webpage: %s ";
    private static final String CSS_NOT_HEADER_NOR_FOOTER = "tr:not(.tabFOOTER):not(.tabHEADER)";
    private static final String MAIN_DIV_ID = "div#ctl00_ctl14_g_e2b3bd01_a486_4a72_ba49_075ec6951fd1";

    public Optional<Pair<Elements, Elements>> processUrl(String url) {
        try {
            final Document document = Jsoup.connect(url).get();
            Elements main = document.select(MAIN_DIV_ID);

            //actual table
            Elements actualTable = main.select("div:nth-of-type(2)").select("table:nth-of-type(3)");
            Elements actualRows = actualTable.select(CSS_NOT_HEADER_NOR_FOOTER);

            //forecast table
            Elements forecastTable = main.select("div:nth-of-type(3)");
            Elements forecastRows = forecastTable.select(CSS_NOT_HEADER_NOR_FOOTER);

            return Optional.of(Pair.of(actualRows, forecastRows));

        } catch (Exception ex) {
            LOGGER.error(String.format(DOWNLOAD_ERROR_MESSAGE, ex.getMessage()));
            return Optional.empty();
        }
    }
}
