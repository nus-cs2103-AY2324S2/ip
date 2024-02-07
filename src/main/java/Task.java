import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

abstract public class Task {
    private static final List<String> dateFormats = List.of(
            "yyyy-MM-dd",
            "dd-MM-yyyy",
            "yyyy-MM-d",
            "d-MM-yyyy",
            "d/MM/yyyy",
            "dd/MM/yyyy",
            "yyyy/MM/dd",
            "yyyy/MM/d",
            "MMM d yyyy",
            "MMM dd yyyy"
    );

    private static final List<String> timeFormats = List.of(
            "HH[:mm[:ss[.SSS]]]",
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

    boolean isDone() {
        return isDone;
    }

    void mark() {
        this.isDone = true;
    }

    void unmark() {
        this.isDone = false;
    }

    static Task lineToTask(String line) throws DukeException {
        String[] lineSplit = line.split(" \\| ");
        switch (lineSplit[0]) {
        case "T":
            Task newTask = new Todo(lineSplit[2]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        case "D":
            if (lineSplit.length < 4) {
                throw new DukeException("    Oops! The file format is wrong!\n");
            }
            newTask = new Deadline(lineSplit[2], lineSplit[3]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        case "E":
            if (lineSplit.length < 5) {
                throw new DukeException("    Oops! The file format is wrong!\n");
            }
            newTask = new Event(lineSplit[2], lineSplit[3], lineSplit[4]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        default:
            throw new DukeException("    Oops! The file format is wrong!\n");
        }
    }

    String taskToLine() {
        String mark = "X";
        if (isDone) {
            mark = "O";
        }
        return mark + " | " + name;
    }

    static LocalDateTime parse(String string) throws DukeException {
        for (String date : dateFormats) {
            for (String time : timeFormats) {
                String format = date + " " + time;
                try {
                    LocalDateTime dateTime = LocalDateTime.parse(string, DateTimeFormatter.ofPattern(format));
                    return dateTime;
                } catch (DateTimeParseException e) {
                    continue;
                }
            }
        }
        throw new DukeException("    Oops! Unable to extract time from the prompt!\n");
    }

    static LocalDate parseDate(String string) throws DukeException {
        for (String date : dateFormats) {
            try {
                LocalDate localDate = LocalDate.parse(string, DateTimeFormatter.ofPattern(date));
                return localDate;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        throw new DukeException("    Oops! Cannot understand the input date!");
    }

    boolean matchDate(LocalDate localDate) {
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
