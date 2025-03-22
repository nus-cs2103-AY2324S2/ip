package riz.data;

/**
 * A class that handles the "ToDo" task type.
 * ToDo consists of just the task description with no deadlines or timeframe.
 */
public class ToDo extends Task {
    public ToDo(String todo) {
        super(todo);
    }

    /**
     * String representation of a ToDo Task.
     * @return the String includes a 'T' to indicate a "ToDo" task.
     */
    @Override
    public String toString() {

        return "T" + super.toString();
    }
}