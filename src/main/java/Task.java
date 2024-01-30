public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected int statusNumber;
    protected String input;

    Task(String description, String input) {
        this.description = description;
        this.isDone = false;
        this.statusNumber = 0;
        this.input = input;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDetails() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public void setComplete() {
        this.isDone = true;
        this.statusNumber = 1;
    }

    public void setIncomplete() {
        this.isDone = false;
        this.statusNumber = 0;
    }

    public void mark() {
        this.setComplete();
        System.out.println("\tNice! I've marked this task as done:\n\t" + this.getDetails());
    }

    public void unmark() {
        this.setIncomplete();
        System.out.println("\tOK, I've marked this task as not done yet:\n\t" + this.getDetails());
    }

    @Override
    public String toString() {
        return this.getDetails();
    }

    public abstract String toFileFormat();
}
