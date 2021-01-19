package task3;


import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class PrinterTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp(){
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    public void tearDown(){
        System.setOut(standardOut);
    }

    @Test
    public void printSingleRowTest() {
        //given
        Elements elements = new Elements();
        elements.add(new Element("td").appendText("100"));
        elements.add(new Element("td").appendText("200"));
        elements.add(new Element("td").appendText("300"));
        //when
        Printer.printSingleRow(12, "Actual", elements);

        //then
        Assertions.assertEquals("Actual: 12 Wind:100 Solar:200 Others:300", outputStreamCaptor.toString().trim());
    }


}