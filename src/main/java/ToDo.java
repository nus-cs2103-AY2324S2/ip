package main.java;

public class ToDo extends Task {
    public ToDo(String taskName) {
        super(taskName);
    }

    private String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() + " " + taskName;
    }
}
