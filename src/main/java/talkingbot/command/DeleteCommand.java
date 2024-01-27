package talkingbot.command;

import talkingbot.task.Task;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

public class DeleteCommand extends Command {
    public DeleteCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        String[] curCommand = super.getCommandArr();
        int deleteIdxInt = Integer.valueOf(curCommand[1]);
        Task removedTask = taskList.removeTask(deleteIdxInt - 1);
        ui.printDeleteTask(removedTask, taskList.getSize());
    }
}
