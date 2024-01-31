package capone.tasks;

public class ToDo extends Task {

    public ToDo(String description, boolean status) {
        super(TaskType.TODO, description, status);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
