package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand implements Command {

    /**
     * Displays all tasks in TaskList.
     *
     * @param list Holds the tasks added.
     * @param ui Displays the tasks listed.
     * @param storage Not used.
     * @return String of response of chatbot.
     * @throws DukeException If no task in TaskList.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("No task in list.\n"
                    + "You may add task with keywords: todo, deadline, event.");
        } else {
            return ui.showTasks(list);
        }
    }

}
