package duke;

/**
 * Class that represents the list command.
 */
public class ListCommand extends Command {
    /**
     * Constructor for the list command.
     */
    public ListCommand() {
        super("", null);
    }

    /**
     * Lists all the tasks from the provided state.
     *
     * @param state The state of the app.
     * @param ui    The user interface of the app.
     * @return Text output of the command
     */
    @Override
    public String execute(State state, Ui ui) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= state.getTasks().size(); i++) {
            Task task = state.getTask(i - 1);
            sb.append(String.format("%d: %s\n", i, task));
        }
        if (state.getTasks().isEmpty()) {
            sb.append("No Tasks! Oopsie!");
        }
        assert sb.length() != 0;
        return sb.toString();
    }
}
