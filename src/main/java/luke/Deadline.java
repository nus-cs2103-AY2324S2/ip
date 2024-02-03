package luke;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a Deadline with the specified name and date.
     *
     * @param name Name of task.
     * @param by LocalDate representing when the deadline is by.
     */
    public Deadline(String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * @return The status of task. ([taskType][isDone] taskname by)
     */
    @Override
    public String fullStatus() { //TODO: add type of task later
        String checkbox;
        if (isDone) {
            checkbox = "[D][X] ";
        } else {
            checkbox = "[D][ ] ";
        }
        return checkbox + name + " (by: " + by + ")";
    }

    @Override
    public boolean equals(Object obj) {
        Deadline deadline = (Deadline) obj;
        if (deadline.name.equals(this.name) && deadline.by.equals(this.by) && (deadline.isDone == this.isDone)) {
            return true;
        }
        return false;
    }
}
