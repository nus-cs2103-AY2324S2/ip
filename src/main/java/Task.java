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
        System.out.println(getStatusIcon() + " " + description);
        System.out.println(line);
    }

    public void unmark(String line) {
        isDone = false;
        System.out.println(line);
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(getStatusIcon() + " " + description);
        System.out.println(line);
    }

    public void displayTask() {
        System.out.println(description);
    }
}
