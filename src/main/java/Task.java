public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description; 
    }

    public String markAsDone() {
        this.isDone = true; 
        return this.toString(); 
    }

    public String markAsUndone() {
        this.isDone = false; 
        return this.toString(); 
    } 

    public String toSave() {
        String strDone = this.isDone ? "1" : "0";
        return strDone + "|" + this.description;
    }
}
