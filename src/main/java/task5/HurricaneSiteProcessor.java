package task5;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class HurricaneSiteProcessor {

    private static final Logger LOGGER = LogManager.getLogger(HurricaneSiteProcessor.class);

    public Map<String, Integer> hurricaneSiteProcessor(String fileName) {
        Map<String, Integer> hurricanesMap = new HashMap<>();
        Pair<Integer, String> hurricaneIdentifier = Pair.of(Integer.MIN_VALUE, "");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                Pair<Integer, String> singleResult = DataParser.processSingleLine(hurricanesMap, line, hurricaneIdentifier);
                hurricaneIdentifier = Pair.of(singleResult);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return hurricanesMap;
    }
}

