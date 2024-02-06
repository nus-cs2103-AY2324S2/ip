import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDateTime by;
    public Deadline(String name, String by) throws GulieException {
        this(name, by, false);
    }

    public Deadline(String name, String by, boolean mark) throws GulieException {
        super(name, mark);
        try {
            this.by = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            throw new GulieException("The datetime that you have given is invalid.");
        }
    }

    @Override
    public String toSaveString() {
        return String.format("D\t%s\t%s", super.toSaveString(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Gulie.getDateTimeFormatter().format(by));
    }
}
