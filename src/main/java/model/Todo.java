package model;

public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String tag) {
        super(description, tag);
    }
    /**
     * {inheritDoc}
     *
     * @return String representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Used to obtain the string in the format that the todo would be saved in.
     * @return String representation of the todo that is savable.
     */
    public String fileSavingString() {
        return "T | " + Integer.toString(super.isDone ? 1 : 0) + " | " + super.description + " | " + super.tag;
    }
}