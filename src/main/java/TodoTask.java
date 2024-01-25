public class TodoTask extends Task {
    public TodoTask(String taskname) {
        super(taskname);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
