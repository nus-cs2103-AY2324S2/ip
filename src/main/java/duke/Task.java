package duke;

/**
 * Represents a Task. Is the parent class of Deadline, Event, and Todo.
 */
public class Task {
    private String name;

    private boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task() {
        this.name = "";
        this.done = false;
    }

    /**
     * Sets this.done to be true.
     */
    public void mark() {
        this.done = true;
    };

    /**
     * Sets this.done to be false.
     */
    public void unmark() {
        this.done = false;
    };

    public String getName() {
        return this.name;
    }


    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    /**
     * Used to represent a Task as a string to be saved in data. If the task is marked as done,
     * a "1" is appended to the front. Otherwise, "0".
     * @return String that represents a task.
     */
    public String getInput() {
        if (this.done) {
            return "1 ";
        } else {
            return "0 ";
        }
    }

}
