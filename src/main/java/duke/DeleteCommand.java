package duke;

/**
 * Class that represents the delete command.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor for the delete command.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        super("");
        this.index = index;
    }

    /**
     * Deletes the task at the given index.
     *
     * @param state The state of the app.
     * @param ui    The user interface of the app.
     * @return Text output of the command
     */
    @Override
    public String execute(State state, Ui ui) {
        Task taskToRemove = state.getTask(index);
        state.getTasks().remove(index);
        return "Mamma-mia!\n" + taskToRemove + "\nRemoved!";
    }
}
