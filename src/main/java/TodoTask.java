package main.java;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }
    @Override
    public String getTask() {
        return (isDone ? "[T][X] " + this.description : "[T][ ] " + this.description);
    }
}
