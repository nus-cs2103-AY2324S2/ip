package bob.task;

import java.time.LocalDate;
import java.time.Month;

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
