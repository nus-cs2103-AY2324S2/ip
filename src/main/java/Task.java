import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Year;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class Task {
    String name;

    boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task() {
        this.name = "";
        this.done = false;
    }

    public void mark() {
        this.done = true;
    };

    public void unmark() {
        this.done = false;
    };


    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    public String getInput() {
        if (this.done) {
            return "1 ";
        } else {
            return "0 ";
        }
    }

}
