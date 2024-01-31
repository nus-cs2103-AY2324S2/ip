import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatus() + " " + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + this.by;
    }

    public static Deadline fromSaveFormat(String[] info) {
        Deadline loadedTask =  new Deadline(info[2], LocalDate.parse(info[3]));
        if (info[1].equals("1")) {
            loadedTask.markAsDone();
        }
        return loadedTask;
    }
}