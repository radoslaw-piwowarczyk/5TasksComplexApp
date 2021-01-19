package task5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DataParserTest {
    @ParameterizedTest
    @CsvSource({"July10112020, May30092020,January01092020","EP0322020"})
    void shouldExtractYearFromEndOfStringAndEquals2020(String date){

        //when
        int actualRowYear = DataParser.getActualRowYear(date);

        //then
        Assertions.assertThat(actualRowYear).isEqualTo(2020);
    }
}
