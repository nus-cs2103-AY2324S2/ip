package duke.task;

public class Task {
    String name;
    String type;
    boolean isComplete;
    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.isComplete = false;
    }
    public void setComplete() {
        this.isComplete = true;
    }
    public void setInComplete() {
        this.isComplete = false;
    }
}