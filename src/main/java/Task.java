public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String[] getFields() {
        String[] result = new String[2];
        result[0] = this.description;
        result[1] = this.isDone ? "Y" : "N";
        return result;
    }

    public String getDescriptionStatus() {
        return (isDone ? "[X] " : "[ ] ") + this.description;
    }

    public String getMarkStatus() {
        return (isDone ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:");
    }

    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }
}
