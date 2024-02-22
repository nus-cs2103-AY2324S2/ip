package talkingbot.command;

import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * Class that represents any invalid commands entered by the user.
 */
public class InvalidCommand extends Command {

    /**
     * Creates a new InvalidCommand instance.
     *
     * @param commandArr String array containing the command.
     */
    public InvalidCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Runs this type of command.
     * Prints an invalid command message to the standard output.
     *
     * @param tasks List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    @Override
    public String runCommand(TaskList tasks, SaveFile saveFile, Ui ui) {
        return ui.getInvalidCmdMsg();
    }
}
