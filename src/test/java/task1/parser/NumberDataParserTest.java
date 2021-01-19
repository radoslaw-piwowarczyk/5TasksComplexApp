package task1.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.UnknownFormatConversionException;

public class NumberDataParserTest {



    @Test
    void shouldThrowExceptionWhenUnsupportedNumberFormat(){
        String stringWithNumberToConvert = "GENERATION(1/234/56)";
        Assertions.assertThrows(UnknownFormatConversionException.class, () -> NumberParser.convertToCorrectNumberFormat(stringWithNumberToConvert));
    }

    @Test
    void shouldExtractNumberFromString(){
        String numberActual = "GENERATION(1,234.56)";
        //when
        String numberExpected = NumberParser.extractNumberFromString(numberActual);
        //then
        Assertions.assertEquals("1,234.56",numberExpected);
    }


}
