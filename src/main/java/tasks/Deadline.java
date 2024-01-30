package tasks;

public class Deadline extends Task {
    public static final String DEADLINE_ICON = "D";
    private String by;

    Deadline(String description, String by) {  // default access modifier
        super(description);
        this.by = by;
    }
    
    Deadline(String description, boolean isDone, String by) {  // default access modifier
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return DEADLINE_ICON;
    }
    
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString() + " (by: " + by + ")";
    }
    
    public String getBy() {
        return by;
    }
}
