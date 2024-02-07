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
        super("", CommandType.MARK);
        this.index = index;
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param state The state of the app.
     * @param ui    The user interface of the app.
     */
    @Override
    public void execute(State state, Ui ui) {
        int taskNo = this.index - 1;
        Task task = state.getTask(taskNo);
        if (task == null) {
            System.out.println("Mamma-mia! This task no exist-o!");
            return;
        }
        task.setDone(true);
        ui.say("Mamma-mai! I've marked it done!");
        ui.say(task.toString());
    }
}
