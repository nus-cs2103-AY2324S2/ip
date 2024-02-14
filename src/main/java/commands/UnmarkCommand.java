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
     * @return
     */
    @Override
    public String execute() {
        try {
            Task toMark = taskList.get(index - 1);

            Ui.printVLine();
            System.out.println("Acknowledged!\n" + toMark.setUndone());
            Ui.printVLine();

            assert !toMark.getStatus() : "Failed at UnmarkCommand";
            return "Acknowledged!\n" + toMark.setUndone();
        } catch (IndexOutOfBoundsException e) {
            String errorMessage = "Oppss...I can't seem to find the task you're looking for. Type 'list' to see the the tasks that you have!";
            Ui.printVLine();
            System.out.println(errorMessage);
            Ui.printVLine();
            return errorMessage;
        }
    }
}
