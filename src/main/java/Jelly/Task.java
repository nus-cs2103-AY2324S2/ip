package jelly;

/**
 * Base class for all tasks
 */
public class Task {
    protected Boolean isDone;
    private String name;

    /**
     * @param name   name of task
     * @param isDone whether task is done
     */
    public Task(String name, boolean isDone) {

        this.name = name;
        this.isDone = isDone;
    }

    /**
     * @return Storage header of task in the format: [type][isDone]
     */
    public String header() {

        return "NULL";
    }

    /**
     * @return Type of task
     */
    public String type() {

        return "N";
    }

    /**
     * @return Additional information for tasks that have deadlines, time periods, other info.
     */
    public String additionalInfo() {

        return "";
    }

    /**
     * Marks task as done
     */
    public void mark() {

        isDone = true;
    }

    /**
     * Marks task as not done
     */
    public void unmark() {

        isDone = false;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {

        String checkbox = this.isDone ? "[x]" : "[ ]";

        return checkbox + " " + name;
    }

    public boolean isDone() {

        return this.isDone;
    }
}
