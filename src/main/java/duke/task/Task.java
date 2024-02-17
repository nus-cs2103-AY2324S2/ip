package duke.task;

public abstract class Task {
    TodoState todoState;
    final String task;

    public String getTask() {
        return task;
    }

    public Task(String task) {
        this.todoState = TodoState.UNDONE;
        this.task = task;
    }

    public Task(String task, TodoState todoState) {
        this.todoState = todoState;
        this.task = task;
    }

    @Override
    public String toString() {
        return "[" + (todoState == TodoState.DONE ? "X" : " ") + "] " + task;
    }

    public abstract String toFileString();
}
