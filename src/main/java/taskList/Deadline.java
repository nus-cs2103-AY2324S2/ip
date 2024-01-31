package taskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime deadlineDate;
    protected String dateTimeString;

    public Deadline(String item, String byDate) {
        super(item);
        try {
            String[] dateString = byDate.split(" ", 2);
            //System.out.println(dateString[1].trim());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");

            LocalDateTime deadlineDate = LocalDateTime.parse(dateString[1].trim(),formatter);
            this.deadlineDate = deadlineDate;
            this.dateTimeString = dateString[0].trim() + ": " + dateString[1].trim();
         } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Please enter a date");
        } catch (DateTimeParseException dte) {
            throw new DateTimeParseException("Date format is incorrect, please try again", byDate, 0, dte);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + dateTimeString + ")";
    }
}
