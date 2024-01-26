package duke;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + super.description + " (by: " + by + ")";
    }

    @Override
    public String toFileString() {
        return "D" + " | " + (isDone ? "1" : "0") + " | " + description + " | " +  by;
    }

    public static Deadline fromFileString(String str) {
        String[] parts = str.split(" \\| ");
        if (!parts[0].equals("D")) {
            return null;
        }
        String description = parts[2].trim();
        LocalDate by = LocalDate.parse(parts[3].trim());
        boolean isDone = parts[1].trim().equals("1");
        Deadline deadline = new Deadline(description, by);
        if (isDone) deadline.markAsDone();
        return deadline;
    }

    public String getDate() {
        return by.toString();
    }
}
