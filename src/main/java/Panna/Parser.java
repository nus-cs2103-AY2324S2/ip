package Panna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    private DateTimeFormatter df;

    Parser(String format) {
        df = DateTimeFormatter.ofPattern(format);
    }
    public LocalDate parse(String dt) {
        return LocalDate.parse(dt, df);
    }
}
