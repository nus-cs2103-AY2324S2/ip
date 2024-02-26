package SamuelBot;

public class Todo extends Task {
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

    @Override
    public String getDescription(){
        return description;
    }

    // Abstract method to check if the task is done
    public boolean isDone(){
        return isDone;
    }
}