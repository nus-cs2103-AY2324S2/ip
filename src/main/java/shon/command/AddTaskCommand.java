package shon.command;

/**
 * The abstract class that represents a command to add a task.
 */
abstract class AddTaskCommand extends Command {
    protected String description;

    /**
     * Creates a new command to add a task.
     *
     * @param description the description of the task to be added.
     */
    public AddTaskCommand(String description) {
        this.description = description;
    }
}
