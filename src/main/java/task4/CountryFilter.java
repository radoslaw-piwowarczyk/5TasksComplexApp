package task4;

import task4.model.Country;
import task4.parser.CountryFileParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URL;
import java.util.Set;

public class CountryFilter {

    private static final Logger LOGGER = LogManager.getLogger(CountryFilter.class);
    private static final URL resource = CountryFilter.class.getClassLoader().getResource("task_B4.txt");

    public static void main(String[] args) {
        final String fileName = resource.getFile();
        System.out.printf("%-25s %s%n", "Country Name", "GDP");
        CountryFileParser countryFileParser = new CountryFileParser();
        Set<Country> countriesWithPrimeGdp = countryFileParser.getCountriesWithPrimeGdp(fileName);
        printResults(countriesWithPrimeGdp);
    }

    private static void printResults(Set<Country> countriesWithPrimeGdp) {
        countriesWithPrimeGdp.forEach(LOGGER::info);
    }
}
