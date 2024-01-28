package task;
import java.time.LocalDateTime;

import util.CsvUtil;
import util.DateTimeUtil;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(boolean isMarked, String description, LocalDateTime by) {
        super(isMarked, description);
        this.by = by;
    }

    @Override
    public CsvUtil format() {
        return new CsvUtil("D", String.valueOf(super.isMarked), super.description,
            DateTimeUtil.format(by));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
            DateTimeUtil.format(by));
    }
}
