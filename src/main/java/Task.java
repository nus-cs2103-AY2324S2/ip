public class Task {
    private String message;
    private boolean isDone;
    Task(String message) {
        this.message = message;
        this.isDone = false;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void setStatus(boolean status) {
        this.isDone = status;
    }
}
