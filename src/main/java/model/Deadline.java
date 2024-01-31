package model;

public class Deadline extends Task {
    public static String typeIcon = "D";
    private String byDateTime;

    public Deadline(String name, String byDateTime) {
        super(name);

        this.byDateTime = byDateTime;
    }

    public Deadline(String name, Boolean isCompleted, String byDateTime) {
        super(name);

        if (isCompleted) {
            super.markTaskCompleted();
        }

        this.byDateTime = byDateTime;
    }

    @Override
    public String toTaskListStringFormat() {
        return String.format("%s|%s|%s",
                Deadline.typeIcon, super.toTaskListStringFormat(), this.byDateTime);
    }

    @Override
    public String getTypeIcon() {
        return Deadline.typeIcon;
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.byDateTime);
    }
}
