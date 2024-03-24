package lilybot.command;

import lilybot.gui.Ui;
import lilybot.parser.Parser;
import lilybot.task.Task;
import lilybot.task.TaskList;


/**
 * Command for deleting a task.
 */
public class DeleteCommand implements Command {
    private static Task deletedTask;
    private Ui ui;
    private TaskList taskList;


    /**
     * Constructs DeleteCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param taskList For tracking the list of tasks.
     */
    public DeleteCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    public void setDeletedTask(Task task) {
        deletedTask = task;
    }

    public static Task getDeletedTask() {
        return deletedTask;
    }

    /**
     * Delete the task from the list.
     *
     * @param command Command entered by users.
     * @return The messages to be displayed after execution.
     */
    @Override
    public String exceute(String command) {
        try {
            int taskNum = Parser.parseInt(command);
            assert taskNum > 0 : "Task number should be at least 1.";
            Task task = taskList.get(taskNum - 1);
            String taskString = task.toString();

            taskList.remove(taskNum - 1);
            int tasklistSize = taskList.getSize();

            //UndoCommand.setLastDeletedTask(task);
            setDeletedTask(task);

            return ui.taskRemoved(taskString, tasklistSize);
        } catch (Exception exc) {
            return ui.invalidInputNumber();
        }
    }
}
