import java.io.Serializable;

public abstract class Task implements Serializable {
    private final static String hRULER = "____________________________________________________________\n";
    private final String description;
    private String status = "[ ]";
    public String writeObject() {
        return String.format("| %s | %s", this.status, this.description);
    }
    public void setStatus(String s) {
        this.status = s;
    }
    public Task(String token) {
        this.description = token;
    }
    public void markDone() {
        this.status = "[X]";
    }
    public void unMarkDone() {
        this.status = "[ ]";
    }
    @Override
    public String toString() {
        return String.format("%s %s", this.status, this.description);
    }
}
