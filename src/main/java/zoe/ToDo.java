package zoe;

/**
 * Subclass of task
 * Creates a todo task when keyed in the following form: todo XYZ
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    public ToDo(String description, String isDoneNumber) {
        super(description);
        this.type = "T";
        assert Integer.parseInt(isDoneNumber) < 2 : "Data file corrupted, invalid state";
        assert Integer.parseInt(isDoneNumber) >= 0 : "Data file corrupted, invalid state";
        this.isDone = isDoneNumber.equals(DoneStates.DONE);
        this.priority = TaskPriority.TODO.getPriority();
    }

    @Override
    public String saveTask() {
        return String.format("todo_%s_%d",  this.description, this.isDoneNumerical());
    }
}
