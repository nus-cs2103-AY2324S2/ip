public class Task {
    protected String description;
    protected int isDone;

    public Task(String description, int isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        if (isDone == 0) {
            return " ";
        } else {
            return "X";
        }
    }

    public void markAsDone() {
        this.isDone = 1;
        System.out.println("This task is marked as done:\n"
                + this);
    }

    public void markAsUndone() {
        this.isDone = 0;
        System.out.println("This task is marked as not done yet:\n"
                + this);
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
