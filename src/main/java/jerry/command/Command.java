package jerry.command;

import jerry.TaskList;
import jerry.Ui;

/**
 * Represents an abstract command that can be executed within the chatbot application.
 * This class serves as a base for all specific command implementations, providing shared structure and functionality.
 */
public abstract class Command {

    protected Ui ui;
    protected TaskList tasks;

    /**
     * Constructs a Command with the necessary UI and task list context.
     *
     * @param ui    The UI component for interacting with the user.
     * @param tasks The task list being manipulated or queried by the command.
     */
    public Command(Ui ui, TaskList tasks) {
        this.ui = ui;
        this.tasks = tasks;
    }

    /**
     * Executes the command's specific logic.
     */
    public abstract String execute();
}
