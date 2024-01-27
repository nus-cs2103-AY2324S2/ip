package talkingbot.command;

import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * A class that represents the "list" command entered by the user.
 */
public class ListCommand extends Command {

    /**
     * Constructor for the ListCommand class.
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
     * @param taskList List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        System.out.println(taskList);
    }
}
