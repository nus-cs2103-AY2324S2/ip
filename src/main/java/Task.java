public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() { //method to get the mark status of task
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() { //method to mark the status
        isDone = true;
    }
    public void unMark() { //method to unmark the status
        isDone = false;
    }
    @Override
    public String toString(){ //method to get the string representation of task
        String s = "[" + this.getStatusIcon() + "] " + this.description;
        return s;
    }
}
