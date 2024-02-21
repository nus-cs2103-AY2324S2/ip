public class Todo extends Task{
    String description;
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}




