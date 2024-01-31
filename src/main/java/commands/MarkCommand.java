package commands;

import task.Task;
import ui.Ui;

/**
 * Encapsulates a command that sets a task to complete status.
 */
public class MarkCommand extends Command {

    public static final String COMMAND = "mark";
    private final int index;

    /**
     * Mark constructor
     * @param i Item to be set to complete.
     */
    public MarkCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute() {
        try {
            Task toMark = tasks.get(index-1);
            Ui.printVLine();
            System.out.println("Acknowledged!!\n" + toMark.setDone());
            Ui.printVLine();
        } catch (IndexOutOfBoundsException e) {
            Ui.printVLine();
            System.out.println("Oppss...I can't seem to find the task you're looking for. Type 'list' to see the the tasks that you have!");
            Ui.printVLine();
        }
    }
}
