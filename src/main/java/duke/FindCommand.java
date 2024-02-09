package duke;

import java.util.List;

public class FindCommand extends Command{
    /**
     * Constructor for the command.
     *
     * @param text The text of the command.
     */
    public FindCommand(String text) {
        super(text, CommandType.FIND);
    }

    @Override
    public void execute(State state, Ui ui) {
        List<Task> tasks = state.getTasks();
        for (Task t : tasks) {
            if (t.getDescription().contains(getText())) {
                ui.say("Found!\n" + t);
                return;
            }
        }
        ui.say("Mamma-mia! No find :(");
    }
}
