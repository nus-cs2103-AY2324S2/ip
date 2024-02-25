package brian.task;

import java.time.LocalDate;

import brian.utils.Util;

public class Deadline extends Task {
    final LocalDate deadline;

    final static int ORDER = 1;

    public Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    public Deadline(String task, LocalDate deadline, TodoState todoState) {
        super(task, todoState);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + "(by: " + deadline + ")";
    }

    @Override
    public String toFileString() {
        return "D | " + (todoState == TodoState.DONE ? "1" : "0") + " | " + task + " | " + Util.formatDate(deadline);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline) {
            return this.deadline.compareTo(((Deadline) o).deadline);
        } else {
            return super.compareTo(o);
        }
    }
}
