import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {

    protected LocalDate by;
    public Deadline(String desc) {
        String[] splitDesc = desc.split(" /by ");
        this.description = splitDesc[0];
        this.by = LocalDate.parse(splitDesc[1]);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}