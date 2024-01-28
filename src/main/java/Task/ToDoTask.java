package Task;

import Task.Task;

public class ToDoTask extends Task {

    public ToDoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String getStringStorageRepresentation() {
        return String.format("T | %s", super.toString());
    }
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

}
