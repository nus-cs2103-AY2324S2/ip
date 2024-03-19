package lilybot.Command;

import lilybot.Parser.Parser;
import lilybot.Task.Task;
import lilybot.Task.TaskList;
import lilybot.Gui.Ui;

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

//    public static void setLastDeletedTask(Task deletedTask) {
//        lastDeletedTask = deletedTask;
//    }

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
            String[] inputBySpace = Parser.parseCommand(lastCommand);
            String firstWord = inputBySpace[0].toUpperCase();

            Command revertCommand;
            switch (firstWord) {
            case "LIST":
                return "Nothing to undo cuz last command is 'LIST'";
            case "FIND":
                return "Nothing to undo cuz last command is 'FIND'";
            case "MARK":
                revertCommand = new UnmarkCommand(ui, lastCommand, taskList);
                return revertCommand.exceute(ui, lastCommand, taskList);
            case "UNMARK":
                revertCommand = new MarkCommand(ui, lastCommand, taskList);
                revertCommand.exceute(ui, lastCommand, taskList);
            case "DELETE":
                int taskNum = Parser.parseInt(lastCommand);
                Task deletedTask = DeleteCommand.getDeletedTask();
                taskList.add(taskNum - 1, deletedTask);
                return ui.printAdded(deletedTask.toString(), taskList);
            case "TODO":
            case "DEADLINE":
            case "EVENT":
                int size = taskList.getSize();
                Task task = taskList.get(size - 1);
                taskList.remove(size - 1);

                String taskString = task.toString();
                return "Noted. The following task is removed:" + "\n"
                        + "  " + taskString + "\n"
                        + "  Now u have " + taskList.getSize() +
                        " tasks in the list.";
            default:
                return "Unexpected command for UNDO.";
            }
        }
    }
}
