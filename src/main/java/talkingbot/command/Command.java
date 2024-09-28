package talkingbot.command;

import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * Class (abstract) that represents any command, both valid and invalid.
 */
public abstract class Command {
    private final String[] commandArr;

    /**
     * Creates a new  for the Command class.
     *
     * @param commandArr String array containing the command.
     */
    public Command(String[] commandArr) {
        this.commandArr = commandArr;
    }

    /**
     * Returns the String array specifying the command to be executed.
     *
     * @return String array containing the command.
     */
    public String[] getCommandArr() {
        return this.commandArr;
    }

    /**
     * Runs command (in subclasses) and returns a resulting String.
     *
     * @param tasks List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    public abstract String runCommand(TaskList tasks, SaveFile saveFile, Ui ui);
}
