package talkingbot.command;

import talkingbot.exception.TalkingBotException;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

public class SaveCommand extends Command {
    public SaveCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        try {
            ui.printSaveOngoingMsg(saveFile.getFileName());
            saveFile.saveTasksToFile(taskList);
            ui.printSaveDoneMsg();
        } catch (TalkingBotException e) {
            ui.printGenericError(e);
        }
    }
}
