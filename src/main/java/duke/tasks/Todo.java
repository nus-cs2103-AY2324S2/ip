package duke.tasks;

public class Todo extends Task {

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    public String toFileString() {
        return String.format("T,%b,%s", this.isDone, this.taskDescription);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.taskDescription);
    }
}
