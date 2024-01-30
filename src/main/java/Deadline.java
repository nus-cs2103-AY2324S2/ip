import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String name, String by) throws DateTimeParseException {
        super(name);
        this.by = LocalDateTime.parse(by, Task.INPUT_TIME_FORMATTER);
    }

    @Override
    public String describe() {
        return String.format("[D]%s (by: %s)",
                super.describe(), this.by.format(Task.OUTPUT_TIME_FORMATTER)
        );
    }

    @Override
    public String toStorageString() {
        return String.format("D,%s,%s", 
                super.toStorageString(), this.by.format(Task.INPUT_TIME_FORMATTER));
    }
}
