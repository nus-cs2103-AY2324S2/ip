package dave.commands;

import dave.TaskList;
import dave.Ui;
import dave.Storage;

import dave.exceptions.ChatbotException;

public abstract class Command {
    /**
     * Executes the command.
     * 
     * @param taskList Task list to add task to.
     * @param ui User interface to display some feedback after executing the command.
     * @param storage Storage to store task information when tasks are modified/deleted.
     * @throws ChatbotException
     * @return Feedback to user about successful command execution.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws ChatbotException;

    /**
     * Checks if the command is the exit command.
     * 
     * @return True only if it is an exit command, false otherwise.
     */
    public abstract boolean isExit();
}
