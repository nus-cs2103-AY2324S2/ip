package talkingbot.command;

import talkingbot.exception.TalkingBotException;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String[] commandArr) {
        super(commandArr);
    }

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
