public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }


    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
