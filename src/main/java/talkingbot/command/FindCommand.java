package talkingbot.command;

import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * Class representing the "find" command entered by the user.
 */
public class FindCommand extends Command {
    /**
     * Constructor for the FindCommand class.
     *
     * @param commandArr String array containing the command.
     */
    public FindCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Runs this command.
     * Gets the String array containing the command before printing
     * a filtered list of tasks that contain matching descriptions.
     *
     * @param taskList List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        String[] commandArr = super.getCommandArr();
        System.out.println(taskList.filterList(commandArr[1]));
    }
}
