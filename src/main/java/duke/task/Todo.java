package duke.task;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String fileString() {
        return "T | " + super.fileString();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}


