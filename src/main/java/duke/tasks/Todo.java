package duke.tasks;

import java.util.ArrayList;

import duke.Duke;
import duke.exceptions.tasks.EmptyDescriptionException;

public class Todo extends Task {
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
    }

    public Todo(String description, boolean isDone) throws EmptyDescriptionException {
        super(description, isDone);
    }

    @Override
    public String serialize() {
        ArrayList<String> taskArgs = new ArrayList<>();

        taskArgs.add("todo");
        taskArgs.add(this.getDescription());
        taskArgs.add(this.isDone() ? "1" : "0");

        return String.join(Duke.ARG_DELIMITER, taskArgs);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
