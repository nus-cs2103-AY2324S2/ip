public class Task {
    private String description;
    private boolean isDone;

    public Task(){};

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // mark or unmark, can enum later
    public void setDone(MarkType markType) {
        switch (markType) {
            case MARK:
                this.isDone = true;
                System.out.println("  Nice! I've marked this task as done:");
                break;
            case UNMARK:
                this.isDone = false;
                System.out.println("  OK, I've marked this task as not done yet:");
                break;
        }
        System.out.println("    " + this);
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] "; // mark done task with X
    }

    @Override
    public String toString() {
        return this.description;
    }
}