package seedu.banter.tasks;

public abstract class Task {  // default access modifier
    private final String description;
    private boolean isDone;
    public static final String IS_DONE = "X";
    public static final String IS_NOT_DONE = " ";
    
    Task(String description) {  // default access modifier
        this.description = description;
    }
    
    Task(String description, boolean isDone) {  // default access modifier
        this.description = description;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }

    String markAsDone() {  // default access modifier
        isDone = true;
        return "Nice! I've marked this task as done:\n  " + this;
    }

    String markAsUndone() {  // default access modifier
        isDone = false;
        return "OK, I've marked this task as not done yet:\n  " + this;
    }

    public String getStatus() {
        return isDone ? "X" : " ";
    }
    
    public abstract String getTaskType();

    public String getDescription() {
        return description;
    }
    
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns true if the description of the task contains the keyword.
     * @param keyword
     * @return True if the description of the task contains the keyword.
     */
    public boolean contains(String keyword) {
        return description.contains(keyword);
    }
}
