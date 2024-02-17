package Fredricksen.tasks.taskType;

public class ToDo extends Task {
    public ToDo(String task, String type, boolean isDone) {
        super(task, type, isDone);
    }

    @Override
    public String toString() {
        return super.toString() + this.getTask();
    }
}