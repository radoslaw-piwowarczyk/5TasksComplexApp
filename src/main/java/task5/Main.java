package task5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Main {
    private static final String FILE_NAME = "hurdat2-nepac-1949-2016-041317.txt";
    private static final String FILE_NOT_FOUND_MESSAGE_FORMAT = "Cannot find file: %s";
    public static final String NAME_SPEED_MESSAGE_FORMAT = "Hurricane name: %s Max speed in knots: %s%n";

    public static void main(String[] args) throws IOException {

        URL resource = Main.class.getClassLoader().getResource(FILE_NAME);
        if (Objects.isNull(resource))
            throw new FileNotFoundException(String.format(FILE_NOT_FOUND_MESSAGE_FORMAT, FILE_NAME));

        HurricaneSiteProcessor hurricaneSiteProcessor = new HurricaneSiteProcessor();
        hurricaneSiteProcessor.hurricaneSiteProcessor(resource.getFile()).forEach((x, y) -> System.out.printf(NAME_SPEED_MESSAGE_FORMAT, x, y));

    }

}
