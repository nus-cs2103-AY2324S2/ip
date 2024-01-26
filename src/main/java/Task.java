public class Task {
    String task;
    Boolean done = false;

    public Task(String task) {
        this.task = task;
    }
    public void mark() {
        this.done = true;
    }
    public void unmark() {
        this.done = false;
    }
    public String getRep() {
        // Returns representation of the task (including done)
        String doneChar = (this.done) ? "X" : " ";
        return String.format("[%s] %s", doneChar, this.task);
    }
}
