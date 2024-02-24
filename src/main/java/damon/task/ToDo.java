package damon.task;

/**
 * Represents a ToDo_task by description and status of task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }


    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

