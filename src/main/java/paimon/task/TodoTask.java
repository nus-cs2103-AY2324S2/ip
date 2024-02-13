package paimon.task;

public class TodoTask extends Task {
    public TodoTask(String description) {
        super(description);
    }
    @Override
    public String getTask() {
        return (isDone ? "[T][X] " + this.description : "[T][ ] " + this.description);
    }

    @Override
    public String toFileString() {
        if (this.isDone) {
            return "T | 1 | " + this.description;
        } else {
            return "T | 0 | " + this.description;
        }
    }
}
