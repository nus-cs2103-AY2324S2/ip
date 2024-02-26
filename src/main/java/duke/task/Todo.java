package duke.task;

/**
 * Class representing Todos
 */
public class Todo extends Task {

    /**
     * Creates an instance
     * @param name Name of the todos task
     */
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


