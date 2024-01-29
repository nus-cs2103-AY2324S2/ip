package tasks;

public class Task {

    private boolean done = false;
    private String name = "";
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return this.name;
    }

    public String setIsDone() {
        if (this.done) {
            return "X";
        } else {
            return " ";
        }
    }

    public void setTaskDone() {
        this.done = true;
    }

    public void setTaskUndone() {
        this.done = false;
    }

    /**
     * Returns Task in correct String format.
     *
     * @return Task in correct String format.
     */
    @Override
    public String toString() {
        return " [" + setIsDone() + "] "+ this.name ;
    }

    public String getType() {
        return "general";
    }

    public String getDone() {
        return Boolean.toString(this.done);
    }
}
