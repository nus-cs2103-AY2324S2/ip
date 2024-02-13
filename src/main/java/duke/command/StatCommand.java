package duke.command;

import java.util.HashMap;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to display statistics.
 */
public class StatCommand extends Command {

    /**
     * Executes the command to display statistics.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        handleTaskBreakdown(tasks, ui);
    }

    private void handleTaskBreakdown(TaskList tasks, Ui ui) {
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
