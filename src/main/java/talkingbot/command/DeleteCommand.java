package talkingbot.command;

import talkingbot.task.Task;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * Class that represents the "delete" command entered by the user.
 */
public class DeleteCommand extends Command {

    /**
     * Creates a new DeleteCommand instance.
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
        String deleteIdxStr = curCommand[1];
        int deleteIdxInt = Integer.valueOf(deleteIdxStr);
        Task removedTask = tasks.removeTask(deleteIdxInt - 1);
        assert !tasks.getTask(deleteIdxInt - 1).equals(removedTask) : "task should already be removed";
        return ui.getDeleteTaskMsg(removedTask, tasks.getSize());
    }
}
