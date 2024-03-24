package lilybot.command;

import lilybot.gui.Ui;
import lilybot.parser.Parser;
import lilybot.task.Task;
import lilybot.task.TaskList;

/**
 * Command for undoing the last command.
 */
public class UndoCommand implements Command {

    private Ui ui;
    private TaskList taskList;


    /**
     * Constructs UndoCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param taskList For tracking the list of tasks.
     */
    public UndoCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }


    /**
     * Undo the last command.
     *
     * @param lastCommand Command entered by users.
     * @return The messages to be displayed after execution.
     */
    @Override
    public String exceute(String lastCommand) {
        if (lastCommand.equals(null)) {
            return ui.noLastCommand();
        }


        String message;
        String[] inputBySpace = Parser.parseCommand(lastCommand);
        String firstWord = inputBySpace[0].toUpperCase();

        Command revertCommand;
        switch (firstWord) {
        case "LIST":
            message = "Nothing to undo cuz last command is 'LIST'";
            break;
        case "FIND":
            message = "Nothing to undo cuz last command is 'FIND'";
            break;
        case "MARK":
            revertCommand = new UnmarkCommand(ui, taskList);
            message = revertCommand.exceute(lastCommand);
            break;
        case "UNMARK":
            revertCommand = new MarkCommand(ui, taskList);
            message = revertCommand.exceute(lastCommand);
            break;
        case "DELETE":
            message = addTaskBack(lastCommand, taskList, ui);
            break;
        case "TODO":
        case "DEADLINE":
        case "EVENT":
            message = removeTask(taskList);
            break;
        default:
            message = "Unexpected command for UNDO.";
            break;
        }
        return message;

    }

    /**
     * Adds the deleted task back to list.
     *
     * @param lastCommand The last command that user entered.
     * @param taskList The list of tasks after the task is deleted.
     * @param ui To be displayed for users.
     * @return The message after adding the task back.
     */
    protected static String addTaskBack(String lastCommand, TaskList taskList, Ui ui) {
        int taskNum = Parser.parseInt(lastCommand);
        Task deletedTask = DeleteCommand.getDeletedTask();
        taskList.add(taskNum - 1, deletedTask);
        return ui.printAdded(deletedTask.toString(), taskList);
    }

    /**
     * Removes the task that was last added.
     *
     * @param taskList The list of tasks after the new task is added.
     * @return The message after removing the task.
     */
    protected static String removeTask(TaskList taskList) {
        int size = taskList.getSize();
        Task task = taskList.get(size - 1);
        taskList.remove(size - 1);
        String taskString = task.toString();
        return "Noted. The following task is removed:" + "\n"
                + "  " + taskString + "\n"
                + "  Now u have " + taskList.getSize()
                + " tasks in the list.";
    }
}
