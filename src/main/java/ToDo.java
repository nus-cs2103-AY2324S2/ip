public class ToDo extends Task {

    String taskType;
    String taskName;
    Boolean isDone;

    public ToDo(String taskName) {
        super(taskName);
        this.taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
