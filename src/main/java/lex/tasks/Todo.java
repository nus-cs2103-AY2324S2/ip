package lex.tasks;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class.
     *
     * @param title The title of the todo.
     */
    public Todo(@JsonProperty("title") String title) {
        super(title);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
