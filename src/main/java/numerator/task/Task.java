package numerator.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    final String description;
    boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    static LocalDateTime parseStringToLocalDatetime(String by) throws DateTimeParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        try {
            return LocalDateTime.parse(by, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(by + " 00:00", dateTimeFormatter);
        }
    }

    static String parseLocalDateTimeToString(LocalDateTime ldt) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return ldt.format(formatter);
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String getSaveString();

}
