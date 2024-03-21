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
    private String command;
    private TaskList taskList;
    //private static Task lastDeletedTask;

    /**
     * Constructs UndoCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param command Command entered by users.
     * @param taskList For tracking the list of tasks.
     */
    public UndoCommand(Ui ui, String command, TaskList taskList) {
        this.ui = ui;
        this.command = command;
        this.taskList = taskList;
    }


    /**
     * Undo the last command.
     *
     * @param ui To be displayed for users.
     * @param lastCommand Command entered by users.
     * @param taskList For tracking the list of tasks.
     * @return The messages to be displayed after execution.
     */
    @Override
    public String exceute(Ui ui, String lastCommand, TaskList taskList) {
        if (lastCommand.equals(null)) {
            return ui.noLastCommand();
        } else {
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
                revertCommand = new UnmarkCommand(ui, lastCommand, taskList);
                message = revertCommand.exceute(ui, lastCommand, taskList);
                break;
            case "UNMARK":
                revertCommand = new MarkCommand(ui, lastCommand, taskList);
                message = revertCommand.exceute(ui, lastCommand, taskList);
                break;
            case "DELETE":
                int taskNum = Parser.parseInt(lastCommand);
                Task deletedTask = DeleteCommand.getDeletedTask();
                taskList.add(taskNum - 1, deletedTask);
                message = ui.printAdded(deletedTask.toString(), taskList);
                break;
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                int size = taskList.getSize();
                Task task = taskList.get(size - 1);
                taskList.remove(size - 1);

                String taskString = task.toString();
                message = "Noted. The following task is removed:" + "\n"
                        + "  " + taskString + "\n"
                        + "  Now u have " + taskList.getSize()
                        + " tasks in the list.";
                break;
            default:
                message = "Unexpected command for UNDO.";
                break;
            }
            return message;
        }
    }
}
