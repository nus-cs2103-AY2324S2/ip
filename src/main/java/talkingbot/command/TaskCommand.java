package talkingbot.command;

import talkingbot.exception.TalkingBotException;
import talkingbot.util.SaveFile;
import talkingbot.task.Task;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * A class that represents any command that creates tasks, i.e.,
 * the "todo", "deadline", and "event" commands entered in by
 * the user.
 */
public class TaskCommand extends Command {

    /**
     * Constructor for the TaskCommand class.
     *
     * @param commandArr String array containing the command.
     */
    public TaskCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Runs this command.
     * Creates a String containing information about the Task,
     * passes it to a static method for a Task to be generated,
     * then tries to add such Task to the list of Tasks while printing to the standard output. Catches
     * an exception if any occur and prints it to the standard output.
     *
     * @param taskList List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
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
