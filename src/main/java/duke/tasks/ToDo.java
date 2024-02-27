package duke.tasks;

public class ToDo extends Task {

    String taskType;
    String taskName;
    Boolean isDone;

    public ToDo(String taskName, Boolean isDone, String taskType) {
        super(taskName, isDone, taskType);
        this.taskType = "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
