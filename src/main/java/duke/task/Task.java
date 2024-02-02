package duke.task;

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

    /**
     * Returns true if the key given is contained in
     * the description of the task.
     *
     * @param key the string key need to be contained.
     * @return a boolean whether the key is contained.
     */
    public boolean hasFind(String key) {
        if (description.contains(key)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String toString(){ //method to get the string representation of task
        String s = "[" + this.getStatusIcon() + "] " + this.description;
        return s;
    }

    public String toWrite(){ //method to get the string representation of task
        String s = this.getStatusIcon() + " | " + this.description;
        return s;
    }
}
