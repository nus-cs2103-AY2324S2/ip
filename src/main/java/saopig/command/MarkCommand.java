package saopig.command;

import java.util.Objects;

import saopig.SaopigInvaildSizeException;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Task;
import saopig.task.TaskList;

/**
 * Represents a command to mark tasks as done.
 */
public class MarkCommand extends Command {

    private String command;
    private int typeIndex; //0 for mark done, 1 for mark undone

    /**
     * Constructs a mark command.
     *
     * @param command   The command.
     * @param typeIndex The type index.
     */
    public MarkCommand(String command, int typeIndex) {
        assert typeIndex == 0 || typeIndex == 1 : "typeIndex should be 0 or 1";
        this.command = command;
        this.typeIndex = typeIndex;
    }

    private static void checkValue(int value, int lowerBound, int upperBound) throws SaopigInvaildSizeException {
        if (value < lowerBound || value > upperBound) {
            throw new SaopigInvaildSizeException("Error");
        }
    }

    /**
     * Marks a task as done.
     * The task list is then saved to the hard disk.
     * If the index is invalid, prints a message.
     *
     * @param input     The input.
     * @param taskList  The task list.
     * @param ui        The user interface.
     * @param storage   The storage.
     */
    public String markTaskAsDone(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            StringBuilder response = new StringBuilder();
            checkValue(input.length(), 6, Integer.MAX_VALUE);
            assert input.length() >= 6 : "Input length should be at least 6";
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task task = taskList.getTask(index);
            task.markAsDone();
            assert Objects.equals(task.getStatusIcon(), "X") : "Task should be marked as done";
            storage.saveTaskList(taskList);
            response.append("\n" + "Oh, splendid! Your task: {")
                    .append(task.toString())
                    .append("} has been marked as done successfully.\n ")
                    .append("Isn't it just wonderful when things go exactly as planned?\n ")
                    .append("I'm so proud of you for getting it done!");
            return response.toString();
        } catch (SaopigInvaildSizeException e) {
            return (e.getMessage()
                    + "\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have forgotten to give an argument for the mark command.\n "
                    + "Don't worry, it happens to most of us.\n "
                    + "Just add the index for the task you'd like to mark, and you'll be all set.\n "
                    + "Please try again, or type 'bye' to exit.");
        } catch (IndexOutOfBoundsException e) {
            return ("\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have given an invalid index for the task list.");
        } catch (NumberFormatException e) {
            return ("\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have given an invalid index for the task list "
                    + "or your input is not a number.");
        }
    }

    /**
     * Unmarks a task as done.
     * The task list is then saved to the hard disk.
     * If the index is invalid, prints a message.
     *
     * @param input     The input.
     * @param taskList  The task list.
     * @param ui        The user interface.
     * @param storage   The storage.
     */
    public String unmarkTaskAsDone(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            StringBuilder response = new StringBuilder();
            checkValue(input.length(), 8, Integer.MAX_VALUE);
            assert input.length() >= 8 : "Input length should be at least 8";
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task task = taskList.getTask(index);
            task.unmarkAsDone();
            assert Objects.equals(task.getStatusIcon(), " ") : "Task should be marked as not done";
            storage.saveTaskList(taskList);
            response.append("\n" + "Oh, you've unmarked task: {")
                    .append(task.toString()).append("}?\n ")
                    .append("No worries at all! It's always okay to reevaluate and adjust your plans.\n ")
                    .append("Flexibility is a sign of strength, you know. Keep up the great work!");
            return response.toString();
        } catch (SaopigInvaildSizeException e) {
            return (e.getMessage()
                    + "\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have forgotten to give an argument for the unmark command.\n "
                    + "Don't worry, it happens to most of us.\n "
                    + "Just add the index for the task you'd like to unmark, and you'll be all set.\n "
                    + "Please try again, or type 'bye' to exit.");
        } catch (IndexOutOfBoundsException e) {
            return ("\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have given an invalid index for the task list.");
        } catch (NumberFormatException e) {
            return ("\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have given an invalid index for the task list "
                    + "or your input is not a number.");
        }
    }

    /**
     * Executes the command to mark tasks as done.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     * @return Response to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (typeIndex == 0) {
            return markTaskAsDone(command, tasks, ui, storage);
        } else {
            return unmarkTaskAsDone(command, tasks, ui, storage);
        }
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
