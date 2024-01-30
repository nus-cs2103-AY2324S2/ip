package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private static final String DEADLINE_MESSAGE = "[D]%s (by: %s)";
    private static final String DEADLINE_FILE_TEMPLATE = "D | %s | %s";
    private final LocalDateTime deadline;
    private final DateTimeFormatter returnFormatter = DateTimeFormatter.ofPattern("EEE HHmm dd/MM/yyyy");

    public Deadline(String description, String deadline) {
        super(description);
        DateTimeFormatter acceptFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
        this.deadline = LocalDateTime.parse(deadline, acceptFormatter);
    }

    public String taskFileTemplate() {
        return String.format(DEADLINE_FILE_TEMPLATE, super.taskFileTemplate(), deadline);
    }

    @Override
    public String toString() {
        return String.format(DEADLINE_MESSAGE, super.toString(), deadline.format(returnFormatter));
    }

}
