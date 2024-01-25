package model;

public class Deadline extends Task {
    private String byDateTime;

    public Deadline(String name, String byDateTime) {
        super(name);

        this.byDateTime = byDateTime;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), this.byDateTime);
    }
}
