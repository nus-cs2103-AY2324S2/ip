package duke;

/**
 * Class that represents the mark command.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Constructor for the mark command.
     *
     * @param index The index of the task to be marked.
     */
    public MarkCommand(int index) {
        super("A");
        this.index = index;
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param state The state of the app.
     * @param ui    The user interface of the app.
     * @return Text output of the command
     */
    @Override
    public String execute(State state, Ui ui) {
        int taskNo = this.index - 1;
        Task task = state.getTask(taskNo);
        if (task == null) {
            return "Mamma-mia! This task no exist-o!";
        }
        task.setDone(true);
        return String.format("Mamma-mai! I've marked it done!\n%s", task);
    }
}
