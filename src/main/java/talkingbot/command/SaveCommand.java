package talkingbot.command;

import talkingbot.exception.TalkingBotException;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * Class that represents the "save" command entered by the user.
 */
public class SaveCommand extends Command {

    /**
     * Creates a new SaveCommand instance.
     *
     * @param commandArr String array containing the command.
     */
    public SaveCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Runs this command.
     * Tries to save the file and print messages to the standard output,
     * and catches any exceptions that may occur before also
     * printing them to the standard output.
     *
     * @param tasks List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    @Override
    public String runCommand(TaskList tasks, SaveFile saveFile, Ui ui) {
        String msg;
        try {
            msg = ui.getSaveOngoingMsg(saveFile.getFileName());
            saveFile.saveTasksToFile(tasks);
            msg += ui.getSaveDoneMsg();
        } catch (TalkingBotException e) {
            msg = ui.getGenericErrorMsg(e);
        }
        return msg;
    }
}
