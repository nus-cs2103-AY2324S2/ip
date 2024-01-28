public class Task {
    private String desc;
    private boolean isCompleted;
    Task(String input) {
        this.desc = input;
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

    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + this.desc;
        } else {
            return "[] " + this.desc;
        }
    }
}
