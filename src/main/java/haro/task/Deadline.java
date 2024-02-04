package haro.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Deadline extends Task {
    String deadline;
    LocalDate deadlineDate;
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;

        try {
            deadlineDate = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            deadlineDate = null;
        }
    }

    public Deadline(String task, String deadline, boolean isDone) {
        super(task, isDone);
        this.deadline = deadline;

        try {
            deadlineDate = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            deadlineDate = null;
        }
    }

    @Override
    public String printTask() {
        String deadlineString;
        if (deadlineDate != null) {
            deadlineString = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            deadlineString = this.deadline;
        }
        return "[D]" + super.printTask() + " (by: " + deadlineString + ")";
    }

    @Override
    public String toString() {
        int marked = this.done ? 1 : 0;
        return "D | " + marked + " | " + this.task + " | " + this.deadline;
    }
}
