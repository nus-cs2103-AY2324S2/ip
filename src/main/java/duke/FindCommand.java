package duke;

import java.util.List;

public class FindCommand extends Command{
    /**
     * Constructor for the command.
     *
     * @param text The text of the command.
     */
    public FindCommand(String text) {
        super(text);
    }

    @Override
    public String execute(State state, Ui ui) {
        List<Task> tasks = state.getTasks();
        for (Task t : tasks) {
            if (t.getDescription().contains(getText())) {
                return "Found!\n" + t;
            }
        }
        return "Mamma-mia! No find :(";
    }
}
