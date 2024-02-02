package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected LocalDateTime endTime;

    public Deadline(String description, LocalDateTime endTime) {
        super(description);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "D |" + super.toString().substring(1) + "| " + endTime.format(formatter);
    }


    public LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
            return null;
        }
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
