package jiayou.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline, a subtype of the task, which has a deadline date.
 * @author Liu Jiayao
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Returns a new event task with the given description.
     *
     * @param description the description of the event task.
     * @param by the deadline date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getByTime() {
        return this.by;
    }

    public void setBy(String newBy) {
        this.by = LocalDate.parse(newBy);
    }

    @Override
    public String toStringForStore() {
        return "D" + super.toStringForStore() + " | by" + this.by.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Deadline)) {
            return false;
        }
        boolean isDescriptionSame = super.getDescription().equals(((Deadline) object).getDescription());
        boolean isBySame = this.by.equals(((Deadline) object).by);
        if (isDescriptionSame & isBySame) {
            return true;
        }
        return false;
    }
}
