import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);

        this.by = LocalDate.parse(by, DateTimeFormatter.ofPattern(" yyyy-MM-dd"));
    }


    @Override
    public String getTaskIcon() {
        return "D";
    }
    @Override
    public String ToString() {
        return super.ToString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toStore() {
        if (isDone) {
            return getTaskIcon() + "/" + "1" + "/" + description + "/ " + by;
        } else {
            return getTaskIcon() + "/" + "0" + "/" + description + "/ " + by;
        }
    }
}
