package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String name, String deadline) throws DukeException {
        super(name);
        this.deadline = Task.parse(deadline);
    }

    @Override
    String taskToLine() {
        return "D | " + super.taskToLine() + " | " + deadline.format(DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    boolean canMatchDate(LocalDate localDate) {
        return deadline.toLocalDate().equals(localDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s) ", deadline
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
