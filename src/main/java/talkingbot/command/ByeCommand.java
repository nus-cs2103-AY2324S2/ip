package talkingbot.command;

import talkingbot.exception.TalkingBotException;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * A class that represents the "bye" command entered by the user.
 */
public class ByeCommand extends Command {

    /**
     * Constructor for the ByeCommand class.
     *
     * @param commandArr String array containing the command.
     */
    public ByeCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Runs this command.
     * Prints a goodbye message, saves current list to a file,
     * and stops the running of the program.
     *
     * @param taskList List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        try {
            ui.printGoodbyeMsg();
            saveFile.saveTasksToFile(taskList);
            ui.setContinueIter(false);
        } catch (TalkingBotException e) {
            ui.printGenericError(e);
        }
    }
}
