package grumblebug;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    private DateTimeFormatter formatter;

    Parser(String format) {
        this.formatter = DateTimeFormatter.ofPattern(format);
    }
    public LocalDate parse(String dt) {
        return LocalDate.parse(dt, this.formatter);
    }
}
