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
        System.out.println("Nice! I've marked this task as done:");
    }

    public void unmark() {
        this.done = "[ ]";
        System.out.println("OK, I've marked this task as not done yet:");
    }
}