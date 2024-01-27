package duke.tasks;

public class Task {
    private String description;
    private boolean isDone;

    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public void doTask() {
        this.isDone = true;
    }
    
    public void undoTask() {
        this.isDone = false;
    }

    private String statusIcon() {
        return isDone ? "X" : " ";
    }
    
    @Override
    public String toString() {
        return "[" + this.statusIcon() + "] " + this.description;
    } 

    public String toSave() {
        return "[" + this.statusIcon() + "]|" + this.description;
    }
}
