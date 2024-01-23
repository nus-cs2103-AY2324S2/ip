public class Task {
    
    private String description;
    private boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
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
    
}

