package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline Class which has a task string and deadline datetime as fields.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter ORIGINAL_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyyHHmm");
    private LocalDateTime deadline;

    /**
     * Constructor that initialises the task string and deadline datetime.
     * @param s Deadline task string
     * @param deadlineString Deadline string to be converted to datetime
     */
    public Deadline(String s, String deadlineString) {
        super(s);

        String cleanedString = deadlineString.replaceAll("\\s", "");
        LocalDateTime deadline = LocalDateTime.parse(cleanedString, ORIGINAL_FORMATTER);
        this.deadline = deadline;
    }

    /**
     * Constructor that initialises the task string and deadline datetime.
     * @param s Deadline task string
     * @param deadline Deadline in datetime
     */
    public Deadline(String s, LocalDateTime deadline) {
        super(s);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline.format(ORIGINAL_FORMATTER));
    }

    @Override
    public String convertToDataStoreLine() {
        return String.format("D|%s|%s|%s", super.convertToDataStoreLine(), super.getTaskString(),
                this.deadline);
    }

    private boolean deadlineEquals(Deadline deadline) {
        return deadline.deadline.equals(this.deadline);
    }

    @Override
    public boolean equals(Task task) {
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return super.equals(deadline) && deadlineEquals(deadline);
        }
        return false;
    }
}
