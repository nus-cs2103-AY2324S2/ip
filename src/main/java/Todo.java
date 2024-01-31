public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }
    @Override
    public String parseToLogRepresentation() {
        int completionStatus = this.isDone ? 1 : 0;
        return "T|" + completionStatus + "|" + this.description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}