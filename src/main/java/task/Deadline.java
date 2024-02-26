package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    private static final DateTimeFormatter ORIGINAL_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyyHHmm");
    public Deadline(String s, String deadlineString) {
        super(s);

        String cleanedString = deadlineString.replaceAll("\\s", "");
        LocalDateTime deadline = LocalDateTime.parse(cleanedString, ORIGINAL_FORMATTER);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline);
    }

    @Override
    public String convertToDataStoreLine() {
        return String.format("D|%s|%s|%s", super.convertToDataStoreLine(), super.getTaskString(),
                this.deadline.format(ORIGINAL_FORMATTER));
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