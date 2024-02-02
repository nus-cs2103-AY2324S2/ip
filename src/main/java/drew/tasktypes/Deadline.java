package drew.tasktypes;

import java.time.LocalDate;
/**
 * This class represents the Deadline task.
 */
public class Deadline extends Task {
    private LocalDate date;
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }
    /**
     * Returns the type, status and description of the Deadline task.
     * @return String of format [D]['Task Status'] 'Task description'.
     */
    @Override
    public String toStatusString() {
        return String.format("[D]%s (by: %s)", super.toStatusString(), this.date);
    }

    @Override
    public String toSaveFormatString() {
        String status = (super.isDone) ? "1" : "0";
        return String.format("D | %s | %s | %s\n", status, super.toString(), this.date);
    }
}
