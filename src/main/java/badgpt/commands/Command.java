package badgpt.commands;

import badgpt.BadGpt;
import badgpt.exceptions.BadException;
import badgpt.exceptions.TaskException;
import badgpt.exceptions.WrongFormatException;
import badgpt.util.TaskList;

/**
 * The parent class of all commands available in the bot.
 */
public abstract class Command {
    protected String rightUsage;
    protected String example;

    /**
     * The method which will perform the action specific to each command.
     *
     * @param bot The chatbot instance to be passed in.
     * @param taskList The task list instance to be passed in.
     * @param args The arguments entered along with the command.
     * @throws BadException If the execution results in an error.
     */
    public abstract void execute(BadGpt bot, TaskList taskList, String args) throws TaskException, WrongFormatException;

    public String getRightUsage() {
        return rightUsage;
    }

    public String getExample() {
        return example;
    }
}
