package commands;

import task.Task;
import ui.Ui;

/**
 * Encapsulates an unmark command.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND = "unmark";
    private final int index;

    public UnmarkCommand(int i) {
        this.index = i;
    }

    /**
     * Sets the task to incomplete status.
     *
     * @return String representation of the outcome of unmarking a task.
     */
    @Override
    public String executeCommand() {
        try {
            Task toMark = taskList.get(index - 1).setUndone();
            assert !toMark.getStatus() : "Failed at UnmarkCommand";
            String unmarkedMessage = "Acknowledged!\n" + toMark;
            printMessage(unmarkedMessage);
            return unmarkedMessage;
        } catch (IndexOutOfBoundsException e) {
            String errorMessage = "Oppss...I can't seem to find the task you're looking for. Type 'list' to see the the tasks that you have!";
            printMessage(errorMessage);
            return errorMessage;
        }
    }

    private static void printMessage(String unmarkedMessage) {
        Ui.printVLine();
        System.out.println(unmarkedMessage);
        Ui.printVLine();
    }
}
