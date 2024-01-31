package storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;
import java.util.Optional;

public class Deadlines extends Task {

    protected LocalDate by;
    protected LocalTime byTime;
    public Deadlines(String originalCommand, String description, String dateTimeBy) {
        super(originalCommand, description);
        String[] splitBy = dateTimeBy.split("-");
        int lenBy = splitBy.length;

        if (lenBy == 3) {
            this.by = LocalDate.parse(String.join("-", splitBy));
        } else if (lenBy == 4) {
            this.by = LocalDate.parse(String.join("-", Arrays.copyOfRange(splitBy,
                    1, lenBy)));
            if (splitBy[0].length() < 5) {
                splitBy[0] = "0" + splitBy[0];
            }
            this.byTime = LocalTime.parse(splitBy[0]);
        } else if (lenBy == 5) {
            this.by = LocalDate.parse(String.join("-", Arrays.copyOfRange(splitBy,
                    2, lenBy)));
            this.byTime = LocalTime.parse(splitBy[1] + " " + splitBy[0], DateTimeFormatter.ofPattern("h:mm a"));
        }
    }
    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(" h:mm a");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + dateFormatter.format(this.by) +
                Optional.ofNullable(byTime).map(timeFormatter::format).orElse("") + ")";
    }
}
