package tasks;

public class Todo extends Task {
    public static final String TODO_ICON = "T";

    Todo(String description) {  // default access modifier
        super(description);
    }
    
    Todo(String description, boolean isDone) {  // default access modifier
        super(description, isDone);
    }

    @Override
    public String getTaskType() {
        return TODO_ICON;
    }
    
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString();
    }
}
