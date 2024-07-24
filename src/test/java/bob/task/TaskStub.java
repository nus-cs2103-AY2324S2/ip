package bob.task;

import java.time.LocalDate;

/**
 * Represents a stub class in place of <code>Task</code>. A <code>TaskStub</code> object corresponds to
 * a concrete instance of a general <code>Task</code>.
 */
public class TaskStub extends Task {
    public TaskStub(String description) {
        super(description);
    }

    @Override
    public boolean isOccurringOn(LocalDate date) {
        return description.equals("a");
    }

    @Override
    public boolean isDueIn(int days) {
        return description.equals("a");
    }

    @Override
    public String getStorageFormat() {
        return "storage format";
    }

    @Override
    public String toString() {
        return description;
    }
}
