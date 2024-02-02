package jiayou.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Represents deadline, a subtype of the task, which has a deadline date.
 * @author Liu Jiayao
 */
public class Deadline extends Task {
    private LocalDate byTime;

    public Deadline(String description, String byTime) {
        super(description);
        this.byTime = LocalDate.parse(byTime);
    }

    public LocalDate getByTime() {
        return this.byTime;
    }

    @Override
    public String toStringForStore() {
        return "D" + super.toStringForStore() + " | by" + this.byTime.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.byTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
