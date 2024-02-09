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
        super("", CommandType.DELETE);
        this.index = index;
    }

    /**
     * Deletes the task at the given index.
     *
     * @param state The state of the app.
     * @param ui    The user interface of the app.
     */
    @Override
    public void execute(State state, Ui ui) {
        Task taskToRemove = state.getTask(index);
        state.getTasks().remove(index);
        ui.say("Mamma-mia!\n" + taskToRemove + "\nRemoved!");
    }
}
