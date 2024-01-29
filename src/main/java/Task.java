public class Task {
    private String desc;
    private String status;

    Task(String desc) {
        this.desc = desc;
        this.status = "[ ]";
    }

    public void mark() {
        this.status = "[X]";
    }

    public void unmark() {
        this.status = "[ ]";
    }

    public String toString() {
        return status + " " + desc;
    }
}
