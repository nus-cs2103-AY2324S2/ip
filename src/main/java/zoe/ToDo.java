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
        this.isDone = isDoneNumber.equals("1");
    }

    @Override
    public String saveTask() {
        return String.format("todo_%s_%d",  this.description, this.isDoneNumerical());
    }
}
