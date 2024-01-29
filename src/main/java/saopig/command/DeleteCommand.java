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
    public void deleteTask(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            checkValue(input.length(), 8, Integer.MAX_VALUE);
            int index = Integer.parseInt(input.substring(7)) - 1;
            ui.printMessage("\n" +
                    "Oh, splendid! Your task: {" + taskList.getTask(index).toString() +
                    "} has been deleted successfully.\n " +
                    "Now you have " + (taskList.getSize() - 1) + " tasks in the list.");
            taskList.deleteTask(index);
            storage.saveTaskList(taskList);
        } catch (SaopigInvaildSizeException e) {
            ui.printMessage("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have forgotten to give an argument for the delete command.\n " +
                    "Don't worry, it happens to most of us.\n " +
                    "Just add the index for the task you'd like to delete, and you'll be all set.\n " +
                    "Please try again, or type 'bye' to exit.");
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list.");
        } catch (NumberFormatException e) {
            ui.printMessage("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list " +
                    "or your input is not a number.");
        } catch (NullPointerException e) {
            ui.printMessage("\n" +
                    "Oopses daisy!\n " +
                    "It seems that taskList do not have anything inside it (return null).");
        }
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @param tasks   The task list to be modified.
     * @param ui      The user interface to be used.
     * @param storage The storage to be used.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        deleteTask(command, tasks, ui, storage);
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
