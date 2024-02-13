package virtue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends VirtueTask {
    private VirtueDateTime by;

    public Deadline(String description, VirtueDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String fileFormat() {
        return "D | " + super.fileFormat() + " | " + by.fileFormat();
    }
}