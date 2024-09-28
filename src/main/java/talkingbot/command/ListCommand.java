package talkingbot.command;

import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * Class that represents the "list" command entered by the user.
 */
public class ListCommand extends Command {

    /**
     * Creates a new ListCommand instance.
     *
     * @param commandArr String array containing the command.
     */
    public ListCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Runs this command.
     * Prints out the list of Tasks to the standard output.
     *
     * @param tasks List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    @Override
    public String runCommand(TaskList tasks, SaveFile saveFile, Ui ui) {
        return tasks.toString();
    }
}
