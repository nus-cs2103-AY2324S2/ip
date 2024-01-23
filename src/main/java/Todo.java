public class Todo extends Task {

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.isDone ? "X" : " ", this.taskDescription);
    }
}
