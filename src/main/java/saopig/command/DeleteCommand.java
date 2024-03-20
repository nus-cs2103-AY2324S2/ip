package saopig.command;

import saopig.SaopigInvaildSizeException;
import saopig.Storage;
import saopig.Ui;
import saopig.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private String command;
    private int typeIndex;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param command   The command to be executed.
     * @param typeIndex The type of task to be deleted.
     */
    public DeleteCommand(String command, int typeIndex) {
        assert typeIndex == 0 : "typeIndex should be 0";
        this.command = command;
        this.typeIndex = typeIndex;
    }

    private static void checkValue(int value, int lowerBound, int upperBound) throws SaopigInvaildSizeException {
        if (value < lowerBound || value > upperBound) {
            throw new SaopigInvaildSizeException("Error");
        }
    }

    /**
     * Deletes a task from the task list.
     * Prints a message to the user to indicate that the task has been deleted successfully.
     * Saves the task list to the hard disk.
     * If the command is invalid, prints a message to the user to indicate that the command is invalid.
     *
     * @param input     The command to be executed.
     * @param taskList  The task list to be modified.
     * @param ui        The user interface to be used.
     * @param storage   The storage to be used.
     */
    public String deleteTask(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            checkValue(input.length(), 8, Integer.MAX_VALUE);
            assert input.length() >= 8 : "Input length should be at least 8";
            int index = Integer.parseInt(input.substring(7)) - 1;
            taskList.deleteTask(index);
            storage.saveTaskList(taskList);
            return getTaskString(taskList, index);
        } catch (SaopigInvaildSizeException e) {
            return (ui.getDeleteNoArgument());
        } catch (IndexOutOfBoundsException e) {
            return (ui.getDeleteInvalidIndex());
        } catch (NumberFormatException e) {
            return (ui.getDeleteInvalidIndex());
        } catch (NullPointerException e) {
            return (ui.getDeleteNullTaskList());
        }
    }

    private static String getTaskString(TaskList taskList, int index) {
        return "\n"
                + "Oh, splendid! Your task: {" + taskList.getTask(index).toString()
                + "} has been deleted successfully.\n "
                + "Now you have " + (taskList.getSize()) + " tasks in the list.";
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @param tasks   The task list to be modified.
     * @param ui      The user interface to be used.
     * @param storage The storage to be used.
     * @return response to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return deleteTask(command, tasks, ui, storage);
    }

    /**
     * Returns false to indicate that the program should continue running.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
