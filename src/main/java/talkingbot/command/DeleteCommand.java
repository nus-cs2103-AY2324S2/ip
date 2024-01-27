package talkingbot.command;

import talkingbot.util.SaveFile;
import talkingbot.task.Task;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

public class DeleteCommand extends Command {

    /**
     * Constructor for the DeleteCommand class.
     *
     * @param commandArr String array containing the command.
     */
    public DeleteCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Runs this command.
     * Gets the index of the Task to be deleted, removes the task from the taskList,
     * and prints out the removed Task.
     *
     * @param taskList List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        String[] curCommand = super.getCommandArr();
        int deleteIdxInt = Integer.valueOf(curCommand[1]);
        Task removedTask = taskList.removeTask(deleteIdxInt - 1);
        ui.printDeleteTask(removedTask, taskList.getSize());
    }
}
