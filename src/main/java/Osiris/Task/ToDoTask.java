package Osiris.Task;

import Osiris.Task.Task;

public class ToDoTask extends Task {

    public ToDoTask(String taskName) {
        super(taskName);
    }

    public ToDoTask(String taskName, boolean isCompleted) {
        super(taskName, isCompleted);
    }

    @Override
    public String getStringStorageRepresentation() {
        return String.format("T | %s", super.getStringStorageRepresentation());
    }
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

}
