public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDescriptionStatus() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + this.description;
    }

}
