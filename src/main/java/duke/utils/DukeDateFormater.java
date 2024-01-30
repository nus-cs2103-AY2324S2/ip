package duke.utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DukeDateFormater {
    public LocalDate stringToDate(String dateStr) {
        return LocalDate.parse(dateStr);
    }

    public String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
