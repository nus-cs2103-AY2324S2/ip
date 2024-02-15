package talkingbot.command;

import talkingbot.task.Task;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * A class that represents both the "mark" and "unmark" commands
 * entered by the user.
 */
public class ModifyMarkCommand extends Command {

    /**
     * Constructor for the ModifyMarkCommand class.
     *
     * @param commandArr String array containing the command.
     */
    public ModifyMarkCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Runs this command.
     * Gets the index of the Task to be modified,
     * modifies the Task's mark, and sets the Task in the same
     * index of the list of Tasks to the modified Task.
     *
     * @param tasks List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    @Override
    public String runCommand(TaskList tasks, SaveFile saveFile, Ui ui) {
        String[] curCommand = super.getCommandArr();
        String markIdxStr = curCommand[1];
        int markIdxInt = Integer.valueOf(markIdxStr);
        Task modifiedTask = tasks.getTask(markIdxInt - 1);
        String msg;
        boolean isMark = curCommand[0].equals("mark");
        if (isMark) {
            modifiedTask.setDone(true);
            msg = ui.getTaskDoneMsg(modifiedTask);
        } else {
            modifiedTask.setDone(false);
            msg = ui.getTaskSetUndoneMsg(modifiedTask);
        }
        tasks.setTask(markIdxInt - 1, modifiedTask);
        return msg;
    }
}
