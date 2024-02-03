package duke.commands;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE = "ok see you bro";

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.print(MESSAGE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
