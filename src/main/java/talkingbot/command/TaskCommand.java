package talkingbot.command;

import talkingbot.exception.TalkingBotException;
import talkingbot.task.Task;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

public class TaskCommand extends Command {
    public TaskCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        String[] curCommand = super.getCommandArr();
        StringBuilder sbDescription = new StringBuilder();
        for (int idx = 1; idx < curCommand.length; idx++) {
            sbDescription.append(curCommand[idx]);
            if (idx < curCommand.length - 1) {
                sbDescription.append(" ");
            }
        }
        String fullDescription = sbDescription.toString();
        try {
            Task curTask = Task.generateTask(fullDescription, curCommand[0]);
            taskList.addTask(curTask);
            ui.printAddTask(curTask, taskList.getSize());
        } catch (TalkingBotException e) {
            ui.printGenericError(e);
        }
    }
}
