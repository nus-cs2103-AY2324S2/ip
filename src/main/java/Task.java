public class Task {
    private String description;
    private boolean isDone;

    public Task(){}

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String isDone, String description) {
        this.isDone = isDone.equals("1");
        this.description = description;
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

    public int getIntIsDone() {
        return isDone ? 1 : 0;
    }

    public String getStatusIcon() {
        return isDone ? "[X] " : "[ ] "; // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}