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

    /**
     * Returns a parsable string of the task. Meant to be used for storage purposes.
     *
     * @return a parsable string representation of the task and all its details
     */
    public String parsableString() {
        String res = "";
        if (this.isDone) {
            res += "X";
        } else {
            res += "V";
        }

        return res + "|" + label;
    }
}
