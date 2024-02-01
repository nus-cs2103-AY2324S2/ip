package Parsing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParserStub {

    protected DateTimeFormatter formatter;
    public DateTimeParserStub() {
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    }
    public LocalDateTime parseDateTime(String timeString) {
        return LocalDateTime.parse(timeString, this.formatter);
    }
}
