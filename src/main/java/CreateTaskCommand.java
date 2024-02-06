/**
 * Represents a command that creates a Task upon execution
 */
public class CreateTaskCommand extends Command {
    private final String commandString;

    /**
     * Creates command to create Task with the arguments supplied by the user
     *
     * @param commandString user input string
     */
    public CreateTaskCommand(String commandString) {
        this.commandString = commandString;
    }

    /**
     * {@inheritDoc}
     *
     * Creates task relying on Task class factory method, adds it to task list.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try{
            Task createdtask = Task.create(this.commandString);
            tasks.add(createdtask);
            this.statusMsg = String.format("Okay. I've deleted this task:\n\t%s\nNow you have %d tasks in the list.",
                    createdtask, tasks.size());
            storage.saveToFile(tasks);
        } catch (CreateTaskException e) {
            this.statusMsg = e.getMessage();
        }
    }
}
