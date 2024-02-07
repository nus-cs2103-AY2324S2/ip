package duke;

public class Todo extends Task {

    protected String by;

    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileFormat() {
        return "T | " +  this.isDone + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
