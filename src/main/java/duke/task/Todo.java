package duke.task;

/**
 * A todo task that contains a description
 */

public class Todo extends Task {

    /**
     * Constructs a Todo class with given parameter
     * @param description A string representation of task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representatino of a todo task;
     * @return string of todo
     */
    @Override
    public String toString() {
        return super.toString() + " Todo : " + this.description;
    }
}
