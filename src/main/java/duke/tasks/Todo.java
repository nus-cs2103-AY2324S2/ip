package duke.tasks;

import java.io.Serializable;

public class Todo extends Task implements Serializable {
    public Todo(String description) {
        super(description);
    }

}