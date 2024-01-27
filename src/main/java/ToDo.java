/**
 * Represents tasks that have no duration and no deadline.
 */
public class ToDo extends Task{
    public ToDo(String todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getData() {
        return "T" + super.getData();
    }
}
