package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to display helps.
 */
public class ErrorCommand extends Command {

    private String errorMessage;

    /**
     * Constructs a new ExitCommand object.
     */
    public ErrorCommand() {

    }
    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        return this.errorMessage;
    }
}

