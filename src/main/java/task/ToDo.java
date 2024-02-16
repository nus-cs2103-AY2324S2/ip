package task;

public class ToDo extends Task {
    
    public ToDo(String taskName) {
        super(taskName);
    }

    public ToDo(boolean isDone, String taskName) {
        super(isDone, taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toData() {
        return "T | " + super.toData(); 
    }
}
