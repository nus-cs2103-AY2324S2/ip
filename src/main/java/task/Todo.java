package task;

import task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super("[T]", description);
    }

    @Override
    public String toString() {
        return this.symbol + super.toString();
    }
}
