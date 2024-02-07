package talkingbot.command;

import talkingbot.task.Task;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * A class that represents the "delete" command entered by the user.
 */
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
     * Gets the index of the Task to be deleted, removes the task from the list of tasks,
     * and prints out the removed Task.
     *
     * @param tasks List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    @Override
    public String runCommand(TaskList tasks, SaveFile saveFile, Ui ui) {
        String[] curCommand = super.getCommandArr();
        int deleteIdxInt = Integer.valueOf(curCommand[1]);
        Task removedTask = tasks.removeTask(deleteIdxInt - 1);
        return ui.getDeleteTaskMsg(removedTask, tasks.getSize());
    }
}
