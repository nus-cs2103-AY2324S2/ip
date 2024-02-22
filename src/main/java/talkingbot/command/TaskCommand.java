package talkingbot.command;

import talkingbot.exception.TalkingBotException;
import talkingbot.task.Task;
import talkingbot.util.SaveFile;
import talkingbot.util.TaskList;
import talkingbot.util.Ui;

/**
 * Class that represents any command that creates tasks, i.e.,
 * the "todo", "deadline", and "event" commands entered in by
 * the user.
 */
public class TaskCommand extends Command {

    /**
     * Creates a new TaskCommand instance.
     *
     * @param commandArr String array containing the command.
     */
    public TaskCommand(String[] commandArr) {
        super(commandArr);
    }

    /**
     * Parses the command String array and returns a formatted version of it.
     *
     * @return Parsed String representing the command.
     */
    private String parseTask() {
        String[] curCommand = super.getCommandArr();
        StringBuilder sbDescription = new StringBuilder();
        for (int i = 1; i < curCommand.length; i++) {
            sbDescription.append(curCommand[i]);
            if (i < curCommand.length - 1) {
                sbDescription.append(" ");
            }
        }
        return sbDescription.toString();
    }

    /**
     * Runs this command.
     * Creates a String containing information about the Task,
     * passes it to a static method for a Task to be generated,
     * then tries to add such Task to the list of Tasks while printing to the standard output. Catches
     * an exception if any occur and prints it to the standard output.
     *
     * @param tasks List of tasks.
     * @param saveFile Abstraction for a file.
     * @param ui User interface.
     */
    @Override
    public String runCommand(TaskList tasks, SaveFile saveFile, Ui ui) {
        String[] curCommand = super.getCommandArr();
        String fullDescription = this.parseTask();
        String msg;
        try {
            Task curTask = Task.generateTask(fullDescription, curCommand[0]);
            tasks.addTask(curTask);
            msg = ui.getAddTaskMsg(curTask, tasks.getSize());
        } catch (TalkingBotException e) {
            msg = ui.getGenericErrorMsg(e);
        }
        return msg;
    }
}
