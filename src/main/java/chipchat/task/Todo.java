package chipchat.task;

import chipchat.action.CommandType;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String dataString() {
        return String.format("%s /isDone %s", CommandType.TODO, super.dataString());
    }
}
