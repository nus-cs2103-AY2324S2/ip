package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {

    /**
     * Display all the Task in TaskList.
     *
     * @param tasks the TaskList where the Task are in
     * @param ui the UI that will be used to display the message
     */
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        ui.listMessage(tasks.toString());
    }

    public boolean isExit() {
        return false;
    }
}
