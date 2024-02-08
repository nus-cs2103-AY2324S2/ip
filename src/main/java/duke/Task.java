package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

abstract public class Task {
    private static final List<String> DATE_FORMATS = List.of(
            "yyyy-MM-dd",
            "yyyy-M-d",
            "dd-MM-yyyy",
            "yyyy-MM-d",
            "d-MM-yyyy",
            "d/MM/yyyy",
            "dd/MM/yyyy",
            "yyyy/MM/dd",
            "yyyy/MM/d"
    );

    private static final List<String> TIME_FORMATS = List.of(
            "HH[:mm[:ss[.SSS]]]",
            "H[:mm[:ss[.SSS]]]",
            "HH[mm[ss]]",
            "hh[:mm[:ss]] a",
            "h[:mm[:ss]] a",
            "hh[mm[ss]] a"
    );

    private final String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    String taskToLine() {
        String mark = "X";
        if (isDone) {
            mark = "O";
        }
        return mark + " | " + name;
    }

    static LocalDateTime parse(String string) throws DukeException {
        for (String date : DATE_FORMATS) {
            for (String time : TIME_FORMATS) {
                String format = date + " " + time;
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(string, DateTimeFormatter.ofPattern(format));
                    return dateTime;
                } catch (DateTimeParseException e) {
                    continue;
                }
            }
        }
        throw new DukeException("Oops! Unable to extract time from the prompt!");
    }

    public static LocalDate parseDate(String string) throws DukeException {
        for (String date : DATE_FORMATS) {
            try {
                LocalDate localDate = LocalDate.parse(string, DateTimeFormatter.ofPattern(date));
                return localDate;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DukeException("Oops! Cannot understand the input date!");
    }

    boolean canMatchDate(LocalDate localDate) {
        return false;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
