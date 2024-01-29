import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;

    public Deadline(String taskName, LocalDateTime by) {
        super(taskName);
        this.by = by;
    }

    private String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return "D | " + (isDone ? "1" : "0") + " | " + taskName + " | " + by.format(formatter);
    }

    public static Deadline fromFileFormat(String fileFormat) throws TaskException {
        String[] parts = fileFormat.split(" \\| ");
        if (parts.length < 3) {
            throw TaskException.forInvalidTaskFormat("ToDo");
        }
        LocalDateTime byTime = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Deadline deadline = new Deadline(parts[2], byTime);
        if (parts[1].equals("1")) {
            deadline.check();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() + " " + taskName
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
