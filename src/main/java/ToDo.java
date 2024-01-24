public class ToDo extends Task {
    public ToDo(String taskTitle) {
        super(taskTitle);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}