package dino.tasks;

import java.io.Serializable;

/**
 * The Todo class represents a task with only description
 */
public class Todo extends Task implements Serializable {
    public Todo(String description) {
        super(description);
    }
}