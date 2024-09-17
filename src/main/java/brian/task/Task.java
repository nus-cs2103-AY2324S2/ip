package brian.task;

public abstract class Task implements Comparable<Task> {
    final String task;
    TodoState todoState;

    public Task(String task) {
        this.todoState = TodoState.UNDONE;
        this.task = task;
    }

    public Task(String task, TodoState todoState) {
        this.todoState = todoState;
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return "[" + (todoState == TodoState.DONE ? "X" : " ") + "] " + task;
    }

    public abstract String toFileString();

    public abstract int getOrder();

    @Override
    public int compareTo(Task o) {
        return this.getOrder() - o.getOrder();
    }
}
