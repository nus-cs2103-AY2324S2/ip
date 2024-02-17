package fluffy.command;

import java.util.HashMap;

import fluffy.FluffyException;
import fluffy.storage.Storage;
import fluffy.tasklist.TaskList;
import fluffy.ui.Ui;

/**
 * Represents a command to display statistics.
 */
public class StatCommand extends Command {

    /**
     * Executes the command to display statistics.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws FluffyException {
        handleTaskBreakdown(tasks, ui);
    }

    private void handleTaskBreakdown(TaskList tasks, Ui ui) {
        if (tasks.getSize() == 0) {
            ui.showMessage("No stats to see right now!");
            return;
        }
        HashMap<String, Integer> data = tasks.getTaskBreakdown();
        ui.showPieChart(data);
    }

    /**
     * Returns whether the command is an exit command.
     * @return Whether the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
