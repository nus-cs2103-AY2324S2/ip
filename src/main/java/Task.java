public class Task {
    
    private String description;
    private boolean completed;
    public final static String DATETIME_FORMAT_OUTPUT = "MMM dd yyyy HH:mm";

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public Task(String description, boolean isCompleted) {
        this.description = description;
        this.completed = isCompleted;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public String getDescription() {
        return this.description;
    }

    public String exportToSave() {
        return this.description;
    }
    
}

