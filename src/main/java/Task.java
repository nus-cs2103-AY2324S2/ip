public class Task {
    private boolean completed;
    private String message;

    public Task(String message) {
        this.completed = false;
        this.message = message;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void markUncompleted() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted() ? "✅" : " ") + "] " + this.message;
    }

}

