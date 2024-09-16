package luke;

import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate byDate;

    /**
     * Creates a Deadline with the specified name and date.
     *
     * @param name Name of task.
     * @param byDate LocalDate representing when the deadline is by.
     */
    public Deadline(String name, LocalDate byDate) {
        super(name);
        this.byDate = byDate;
    }

    /**
     * @return The status of task. ([taskType][isDone] taskname by)
     */
    @Override
    public String getFullStatus() { //TODO: add type of task later
        String checkbox;
        if (isDone) {
            checkbox = "[D][X] ";
        } else {
            checkbox = "[D][ ] ";
        }
        return checkbox + name + " (by: " + byDate + ")";
    }

    @Override
    public boolean equals(Object obj) {
        Deadline deadline = (Deadline) obj;
        if (deadline.name.equals(this.name) && deadline.byDate.equals(this.byDate) && (deadline.isDone == this.isDone)) {
            return true;
        }
        return false;
    }
}
