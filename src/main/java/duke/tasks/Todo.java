package duke.tasks;

public class Todo extends Task {
    public Todo(Boolean status, String detail) {
        super(status, detail);
    }

    @Override
    public String inFileStringFormat() {
        return "T|" + super.inFileStringFormat();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
