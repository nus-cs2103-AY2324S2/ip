public class Task {
    private String name;
    private boolean status;

    public Task(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public void updateStatus() {
        this.status = !this.status;
    }
} 
