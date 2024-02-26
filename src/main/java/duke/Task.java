package duke;

/**
 * Represents a Task. Is the parent class of Deadline, Event, and Todo.
 */
public class Task {
    private String name;

    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task() {
        this.name = "";
        this.isDone = false;
    }

    /**
     * Sets this.isDone to be true.
     */
    public void mark() {
        this.isDone = true;
    };

    /**
     * Sets this.isDone to be false.
     */
    public void unmark() {
        this.isDone = false;
    };

    public String getName() {
        return this.name;
    }


    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    /**
     * Used to represent a Task as a string to be saved in data. If the task is marked as isDone,
     * a "1" is appended to the front. Otherwise, "0".
     * @return String that represents a task.
     */
    public String getInput() {
        if (this.isDone) {
            return "1 ";
        } else {
            return "0 ";
        }
    }

}
