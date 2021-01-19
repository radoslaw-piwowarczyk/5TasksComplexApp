package task1.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.UnknownFormatConversionException;

public class DateDataParserTest {

    @Test
    void shouldThrowExceptionWhenUnsupportedDateFormat(){
        String date = "23/09/1999";
        Assertions.assertThrows(UnknownFormatConversionException.class, () -> DateParser.extractDateFromString(date));
    }

    @Test
    void shouldExtractDateFromString(){
        String dateToTest = "10102017T0000";
        //when
        String dateExpected = DateParser.extractDateFromString(dateToTest);
        //then
        Assertions.assertEquals("10-10-2017",dateExpected);
    }
}
