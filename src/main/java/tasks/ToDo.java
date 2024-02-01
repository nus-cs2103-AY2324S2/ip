package tasks;

public class ToDo extends Task {
    public ToDo(String description) {

        super(description, 'T');
    }

    public ToDo(String description, Boolean isDone) {

        super(description, isDone,'T');
    }
    @Override
    public String toString() {

        return super.toString() + "\n";
    }
}