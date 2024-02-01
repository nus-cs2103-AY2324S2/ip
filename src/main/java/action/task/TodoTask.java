package action.task;

public class TodoTask extends Task{
    public TodoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
