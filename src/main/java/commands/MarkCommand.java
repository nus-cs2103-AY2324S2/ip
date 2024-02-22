package commands;

import task.Task;
import ui.Ui;

/**
 * Encapsulates a mark command.
 */
public class MarkCommand extends Command {

    public static final String COMMAND = "mark";
    private final int index;

    public MarkCommand(int i) {
        this.index = i;
    }

    /**
     * Sets this task to complete status.
     *
     * @return String representing the outcome of mark.
     */
    @Override
    public String executeCommand() {
        try {
            Task toMark = taskList.get(index - 1).setDone();
            assert toMark.getStatus() : "Failed at MarkCommand";
            String markedMessage = "Acknowledged!!\n" + toMark;
            printMessage(markedMessage);
            return markedMessage;
        } catch (IndexOutOfBoundsException e) {
            String errorMessage = "Oppss...I can't seem to find the task you're looking for. Type 'list' to see the the tasks that you have!";
            printMessage(errorMessage);
            return errorMessage;
        }
    }

    private static void printMessage(String markedMessage) {
        Ui.printVLine();
        System.out.println(markedMessage);
        Ui.printVLine();
    }
}
