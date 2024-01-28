abstract public class Task {
    protected final String desc;
    private boolean isCompleted;
    Task(String taskName) {
        this.desc = taskName;
        this.isCompleted = false;
    }

    public void markAsComplete() {
        this.isCompleted = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + this.toString());
    }

    public void markAsIncomplete() {
        this.isCompleted = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + this.toString());
    }

    public String getStatus() {
        return this.isCompleted
                ? "X"
                : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "]" + this.desc;
    }
}
