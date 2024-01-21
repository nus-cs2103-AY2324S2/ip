package Gluti.utils;

public class Todo extends Task {

    protected String by;

    public Todo(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}