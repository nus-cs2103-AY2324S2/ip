public class Task {
    private String what;
    private String done;

    public Task(String what) {
        this.what = what;
        this.done = "[ ]";
    }

    public String showAll() {
        return this.done + " " + this.what;
    }

    public void mark() {
        this.done = "[X]";
    }

    public void unmark() {
        this.done = "[ ]";
    }
}