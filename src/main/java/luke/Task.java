package luke;

import java.io.Serializable;

public class Task implements Serializable {
    protected boolean isDone = false;
    protected String name = "";


    /**
     * Creates a task with the given name.
     *
     * @param name Name of task.
     */
    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks this task as complete.
     */
    public void complete() {
        isDone = true;
        //System.out.println("It's about time you got this done. ");
    }

    /**
     * Gives the full status of task. ([isDone] taskname)
     *
     * @return The status of task. ([isDone] taskname)
     */
    public String fullStatus() { //TODO: add type of task later
        String checkbox;
        if (isDone) {
            checkbox = "[X] ";
        } else {
            checkbox = "[ ] ";
        }
        return checkbox + name;
    }

    @Override
    public String toString() {
        return name;
    }
}
