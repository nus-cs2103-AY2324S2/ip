public class Task {
    String description;
    boolean done;
    String display;

    public Task(String description) {
        this.description = description;
        this.done = false;
        this.display = "[ ]";
    }

    public void changeDone() {
        this.done = !this.done;
        if (this.done) {
            this.display = "[X]";
        } else {
            this.display = "[ ]";
        }
    }
}
