package taskList.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime deadlineDate;
    protected String byDateString;

    public Deadline(String item, String byDate) {
        super(item);
        try {
            String byDateString = byDate.substring(byDate.indexOf("/to") + 3, byDate.length());
            if (byDateString.trim() == "") {
                throw new EmptyDateException();
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
            LocalDateTime deadlineDate = LocalDateTime.parse(byDateString,formatter);
            this.deadlineDate = deadlineDate;
            this.byDateString = byDateString.trim();
         } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Please enter a date");
        } catch (DateTimeParseException dte) {
            throw new DateTimeParseException("Date format is incorrect, please try again", byDate, 0, dte);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDateString + ")";
    }
}
