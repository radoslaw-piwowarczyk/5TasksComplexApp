package task1;

import task1.parser.DateParser;
import task1.parser.NumberParser;
import task1.printer.Printer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class ParserFromHtml {
    private static final DateParser dataParser = new DateParser();
    private static final NumberParser numberParser = new NumberParser();
    private static final Printer printer = new Printer(dataParser, numberParser);
    static final URL resource = ParserFromHtml.class.getClassLoader().getResource("task_A1.html");

    public static void main(String[] args) throws IOException, URISyntaxException {
        final File input = new File(Objects.requireNonNull(resource).toURI());
        final Document doc = Jsoup.parse(input, "UTF-8", "");
        final Elements rows = doc.select("tr");

        rows.forEach(printer::printSingleRow);
    }
}
