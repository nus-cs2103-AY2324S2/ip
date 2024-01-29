public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    public abstract String writeToFileString();

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
    /* toggle status and returns string */
    public String toggle() {
        String output;
        if (this.isDone) {
            this.isDone = false;
            output = "____________________________________________________________\n" + 
            " OK, I've marked this task as not done yet:\n" + "   " + this + "\n" + 
            "____________________________________________________________\n";
        } else {
            this.isDone = true;
            output = "____________________________________________________________\n" + 
            " Nice! I've marked this task as done:\n" + "   " + this + "\n" + 
            "____________________________________________________________\n";
        }
        return output;
    }

}
