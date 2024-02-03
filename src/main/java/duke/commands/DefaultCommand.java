package duke.commands;

import duke.TaskList;
import duke.Ui;

public class DefaultCommand extends Command {
    public static final String MESSAGE = "what are u saying";

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.print(MESSAGE);
    }
}
