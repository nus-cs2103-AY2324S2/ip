package talkingbot.command;

import talkingbot.task.Task;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

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
     * @param taskList List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
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
