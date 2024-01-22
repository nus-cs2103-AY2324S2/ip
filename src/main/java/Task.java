public class Task {
    private String description;
    private boolean status;

    public Task(String description, boolean status) {
        this.description = description;
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void updateStatus() {
        this.status = !this.status;
    }
}
