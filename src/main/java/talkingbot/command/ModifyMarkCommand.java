package talkingbot.command;

import talkingbot.util.TaskList;
import talkingbot.util.SaveFile;
import talkingbot.util.Ui;
import talkingbot.task.Task;

public class ModifyMarkCommand extends Command {
    public ModifyMarkCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        String[] curCommand = super.getCommandArr();
        int markIdxInt = Integer.valueOf(curCommand[1]);
        Task modifiedTask = taskList.getTask(markIdxInt - 1);
        if (curCommand[0].equals("mark")) {
            modifiedTask.setDone(true);
            ui.printTaskSetDone(modifiedTask);
        } else {
            modifiedTask.setDone(false);
            ui.printTaskSetUndone(modifiedTask);
        }
        taskList.setTask(markIdxInt - 1, modifiedTask);
    }
}
