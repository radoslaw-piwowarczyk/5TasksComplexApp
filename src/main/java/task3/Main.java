package task3;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.select.Elements;

public class Main {
    private static Printer printer = new Printer();
    private static ForecastProcessor processor = new ForecastProcessor();
    private static final String URL = "http://www.mercado.ren.pt/EN/Electr/MarketInfo/Gen/Pages/Forecast.aspx";

    public static void main(String[] args) {
        Pair<Elements, Elements> powerGenerationData = processor
                .processUrl(URL)
                .orElseThrow(() -> new RuntimeException("Unknown URL process error"));
        printer.printActualAndForecast(powerGenerationData);

    }
}
