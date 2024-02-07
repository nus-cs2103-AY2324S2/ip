package talkingbot.command;

import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * A class that represents any invalid commands entered by the user.
 */
public class InvalidCommand extends Command {

    /**
     * Constructor for the InvalidCommand class.
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
