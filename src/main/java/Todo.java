public class Todo extends Task {
    public Todo(String description,boolean isDone) {
        super(description);
        isTodo = true;
        this.isDone = isDone;
    }
}
