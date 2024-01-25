public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void markDone(String line) {
        isDone = true;
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this.toString());
        System.out.println(line);
    }

    public void unmark(String line) {
        isDone = false;
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(this.toString());
        System.out.println(line);
    }

    public void displayTask(int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + this.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
