public class Todo extends Task {
    public Todo(String description,boolean isDone) {
        super(description, isDone);
        isTodo = true;
    }
}
