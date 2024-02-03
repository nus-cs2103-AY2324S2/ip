package talkingbot.command;

import talkingbot.exception.TalkingBotException;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * A class that represents the "save" command entered by the user.
 */
public class SaveCommand extends Command {

    /**
     * Constructor for the SaveCommand class.
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
    public void runCommand(TaskList tasks, SaveFile saveFile, Ui ui) {
        try {
            ui.printSaveOngoingMsg(saveFile.getFileName());
            saveFile.saveTasksToFile(tasks);
            ui.printSaveDoneMsg();
        } catch (TalkingBotException e) {
            ui.printGenericError(e);
        }
    }
}
