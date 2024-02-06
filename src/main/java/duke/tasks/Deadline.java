package duke.tasks;

import duke.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String taskDescription, LocalDate deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        return String.format("T,%b,%s,%s", isDone, taskDescription, deadline);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.isDone ? "X" : " ", this.taskDescription, this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
