package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a <code>Deadline</code> with a name and when it is due.
     *
     * @param n Specified deadline name.
     * @param by Due date.
     */
    public Deadline(String n, LocalDate by) {
        super(n);
        this.by = by;
    }

    /**
     * Constructs a <code>Deadline</code> with a name, when it is due,
     * and whether it has been completed.
     *
     * @param n Specified deadline name.
     * @param d If deadline is done.
     * @param by Due date.
     */
    public Deadline(String n, boolean d, LocalDate by) {
        super(n, d);
        this.by = by;
    }

    /**
     * Returns a <code>LocalDate</code> of the due date.
     *
     * @return Due date of deadline.
     */
    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                                super.toString(),
                                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                            );
    }
}
