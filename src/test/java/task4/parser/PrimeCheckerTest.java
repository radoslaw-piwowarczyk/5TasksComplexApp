package task4.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeCheckerTest {

    @Test
    void shouldBePrimeFor4936543() {
        //when
        boolean result = PrimeChecker.isPrime(4936543);

        //then
        Assertions.assertTrue(result);
    }

    @Test
    void shouldNotBePrimeFor511397() {
        //when
        boolean result = PrimeChecker.isPrime(511397);

        //then
        Assertions.assertFalse(result);
    }
}
