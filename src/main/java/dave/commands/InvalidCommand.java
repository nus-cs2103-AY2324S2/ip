package dave.commands;

import dave.Ui;
import dave.Storage;
import dave.TaskList;

import dave.exceptions.ChatbotException;

public class InvalidCommand extends Command {
    /** The Exception that happens due to an invalid command. */
    private ChatbotException exc;

    /**
     * Creates new InvalidCommand.
     * Takes in the Exception to show the error or message to user.
     * 
     * @param exc Exception that occurred.
     */
    public InvalidCommand(ChatbotException exc) {
        this.exc = exc;
    }

    /**
     * {@inheritDoc}
     * Prints the error or message to user on why the command was invalid.
     * 
     * @return Show the error that occurred due to invalid command execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return exc.getMessage();
    }

    /**
     * {@inheritDoc}
     * Not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
