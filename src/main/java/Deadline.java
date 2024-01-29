import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate time;

    public Deadline(boolean done, String name, String time) {
        super(done, name);
        this.time = LocalDate.parse(time);
    }

    public Deadline(String name, String time) {
        super(name);
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toSavedString() {
        return String.format("D,%s,%s,%s"
                , this.done ? '1' : '0'
                , this.name
                , this.time.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)\n"
                , this.done ? "X" : " "
                , this.name
                , this.time.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
