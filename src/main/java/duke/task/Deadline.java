package duke.task;

import duke.utils.Util;

import java.time.LocalDate;

public class Deadline extends Task {
    final LocalDate deadline;

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
}
