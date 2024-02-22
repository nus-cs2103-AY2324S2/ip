package talkingbot.command;

import talkingbot.exception.TalkingBotException;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * Class that represents the "bye" command entered by the user.
 */
public class ByeCommand extends Command {

    /**
     * Creates a new ByeCommand instance.
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
     * @param tasks List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    @Override
    public String runCommand(TaskList tasks, SaveFile saveFile, Ui ui) {
        String msg;
        try {
            msg = ui.getGoodbyeMsg();
            saveFile.saveTasksToFile(tasks);
        } catch (TalkingBotException e) {
            msg = ui.getGenericErrorMsg(e);
        }
        return msg;
    }
}
