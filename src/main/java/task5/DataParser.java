package task5;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public class DataParser {

    static int getActualRowYear(String value) {
        int actualRowYear;
        String basinYearField = value.trim();
        actualRowYear = Integer.parseInt(basinYearField.substring(basinYearField.length() - 4));
        return actualRowYear;
    }

    static Pair<Integer, String> processSingleLine(Map<String, Integer> hurricanesMap, String line, Pair<Integer, String> hurricaneIdentifier) {
        String[] values = line.split(",");
        int actualRowYear = hurricaneIdentifier.getLeft();
        String hurricaneName = hurricaneIdentifier.getRight();

        if (values.length == 3) {
            actualRowYear = getActualRowYear(values[0]);
            hurricaneName = values[1].trim();
        } else if (actualRowYear >= 2016 && hurricaneName.endsWith("A")) {

            int maxSpeed = Integer.parseInt(values[6].trim());

            hurricanesMap.computeIfPresent(hurricaneName, (key, val) -> (val < maxSpeed) ? maxSpeed : val);
            hurricanesMap.putIfAbsent(hurricaneName, maxSpeed);
        }
        return Pair.of(actualRowYear, hurricaneName);
    }
}
