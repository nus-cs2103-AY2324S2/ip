public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public String markAsDone() {
        this.isDone = !isDone;
        String msg = this.isDone ? "Nice! I've marked this task as done:\n"
                : "OK, I've marked this task as not done yet:\n";

        return msg + "[" + this.getStatusIcon() + "] " + this.description;
    }
    public String getType() {
        return "";
    }
    public String getExtraInfoShortened() {
        return "";
    }

    public String getExtraInfo() {
        return "";
    }

}
