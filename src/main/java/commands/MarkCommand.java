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
     * @return
     */
    @Override
    public String execute() {
        try {
            Task toMark = taskList.get(index - 1);

            Ui.printVLine();
            System.out.println("Acknowledged!!\n" + toMark.setDone());
            Ui.printVLine();

            assert toMark.getStatus() : "Failed at MarkCommand";
            return "Acknowledged!!\n" + toMark.setDone();
        } catch (IndexOutOfBoundsException e) {
            String errorMessage = "Oppss...I can't seem to find the task you're looking for. Type 'list' to see the the tasks that you have!";
            Ui.printVLine();
            System.out.println(errorMessage);
            Ui.printVLine();
            return errorMessage;
        }
    }
}
