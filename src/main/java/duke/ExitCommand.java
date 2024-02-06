package duke;

/**
 * Represents the command to exit the chatbot program.
 */
public class ExitCommand extends Command {
    public ExitCommand() {

    }

    /**
     * Executes the saving of tasks to the storage and sets isExit to true.
     *
     * @param tasks Tasklist with tasks to be saved.
     * @param ui User interface.
     * @param storage Storage to store Tasklist content.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        setExit(true);
    }
}
