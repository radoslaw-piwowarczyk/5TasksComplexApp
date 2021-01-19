package task4.parser;

import task4.CountryFilterException;
import task4.model.Country;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class CountryFileParser {

    private static final Logger LOGGER = LogManager.getLogger(CountryFileParser.class);
    private static final String FILE_READ_ERROR_MESSAGE = "Cannot read file %s";
    private static final String GDP_ERROR_MESSAGE = "GDP value is empty.";
    private static final String COUNTRY_NAME_ERROR_MESSAGE = "CountryName value is empty.";


    private int getGdp(String[] parts) {
        String gdp = parts[1];
        if (!StringUtils.isEmpty(gdp)) {
            return Integer.parseInt(gdp.replaceAll(",", "").trim());
        } else {
            throw new CountryParserException(GDP_ERROR_MESSAGE);
        }
    }

    private String getCountryName(String[] parts) {
        String countryName = parts[0];
        if (!StringUtils.isEmpty(countryName)) {
            return parts[0].trim();
        } else {
            throw new CountryParserException(COUNTRY_NAME_ERROR_MESSAGE);
        }
    }

    private Optional<Country> extractCountryFromLine(String line) {
        String[] parts = line.split("\t");
        try {
            String countryName = getCountryName(parts);
            int gdpValue = getGdp(parts);
            if (PrimeChecker.isPrime(gdpValue)) {
                return Optional.of(new Country(countryName, gdpValue));
            }
        } catch (CountryParserException e) {
            LOGGER.error(e.getMessage());
        }
        return Optional.empty();
    }

    public Set<Country> getCountriesWithPrimeGdp(String fileName) {
        Set<Country> countrySet = new TreeSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            while ((line = br.readLine()) != null) {
                if (lineNumber == 0) {
                    lineNumber++;
                    continue;
                }
                Optional<Country> country = extractCountryFromLine(line);
                country.ifPresent(countrySet::add);
            }
        } catch (IOException e) {
            LOGGER.error(String.format(FILE_READ_ERROR_MESSAGE, fileName));
            throw new CountryFilterException(e);
        }
        return countrySet;
    }
}
