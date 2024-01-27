package talkingbot.task;

import talkingbot.type.TaskType;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone, TaskType.TODO);
    }


    @Override
    public String getSaveFileString() {
        return String.format("T | %d | %s", super.getDoneAsInt(),
                super.getDescription());
    }
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
