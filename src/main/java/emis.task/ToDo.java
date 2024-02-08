package main.java.emis.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String storeString() {
        return "T | " + super.storeString();
    }

    @Override
    public String toString() {
        return"[T]" + super.toString();
    }
}