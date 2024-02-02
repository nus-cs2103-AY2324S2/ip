package Oak.task.model;

import Oak.Utility.DateTimeUtility;

import java.time.LocalDateTime;

public class Deadline extends Task {
    public static String typeIcon = "D";
    private LocalDateTime byDateTime;

    public Deadline(String name, String byDateTime) {
        super(name);

        this.byDateTime = DateTimeUtility.parseStringToLocalDateTime(byDateTime);
    }

    public Deadline(String name, Boolean isCompleted, String byDateTime) {
        super(name);

        if (isCompleted) {
            super.markTaskCompleted();
        }

        this.byDateTime = DateTimeUtility.parseStringToLocalDateTime(byDateTime);
    }

    @Override
    public String toTaskListStringFormat() {
        return String.format("%s|%s|%s",
                Deadline.typeIcon, super.toTaskListStringFormat(), this.byDateTime.toString());
    }

    @Override
    public String getTypeIcon() {
        return Deadline.typeIcon;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)",
                super.toString(), DateTimeUtility.parseLocalDateTimeToString(this.byDateTime));
    }
}
