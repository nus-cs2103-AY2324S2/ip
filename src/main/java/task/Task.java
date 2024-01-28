package task;

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
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            return LocalDateTime.parse(by, formatterWithoutTime);
        } catch (DateTimeParseException e) {
            return LocalDateTime.parse(by, formatterWithTime);
        }
    }

    static String parseLocalDateTimeToString(LocalDateTime ldt) throws DateTimeException {
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            return ldt.format(formatterWithoutTime);
        } catch (DateTimeException e) {
            return ldt.format(formatterWithTime);
        }
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
