public abstract class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
        System.out.println("     Nice! I've marked this task as done:\n       " + this.toString());
    }

    public void unmarkDone() {
        this.isDone = false;
        System.out.println("     OK, I've marked this task as not done yet:\n       " + this.toString());
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.name;
    }
}
