public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    /* toggle status and returns string */
    public String toggle() {
        String output = "";
        if (this.isDone) {
            this.isDone = false;
            output = "OK, I've marked this task as not done yet:\n" + this.getStatusIcon() + " " + this.description + "\n";
        } else {
            this.isDone = true;
            output = "Nice! I've marked this task as done:\n" + this.getStatusIcon() + " " + this.description + "\n";
        }
        return output;
    }
}
