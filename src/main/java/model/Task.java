package model;

public class Task {
    private String label;
    private boolean isDone;

    public Task(String label) {
        this.label = label;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return a string representation of the task and all its details
     */
    @Override
    public String toString() {
        String res = "";
        if (this.isDone) {
            res += "[X]";
        } else {
            res += "[ ]";
        }

        return res + " " + label;
    }
}
