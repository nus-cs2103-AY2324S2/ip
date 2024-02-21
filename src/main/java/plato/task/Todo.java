package plato.task;

import plato.storage.SaveType;

/**
 * Create a todo class that only has a name.
 */
public class Todo extends Task {

    /**
     * Creates a simple todo task.
     *
     * @param description Name of the Task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveFile() {
        return "T" + "|" + super.done() + "|" + super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public SaveType getType() {
        return SaveType.TODO;
    }
}
