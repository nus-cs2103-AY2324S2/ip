package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * The type List command.
 * To list all tasks in task list
 */
public class ListCommand extends Command{
    /**
     * Instantiates a new List command.
     */
    public ListCommand(){
    }

    /**
     * Execute.
     *
     * @param taskList the task list
     * @param ui       the ui
     * @param storage  the storage
     * @throws DukeException the duke exception
     */
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:");
        String taskoutput = taskList.printOutput();
        sb.append("\n").append(taskoutput);
        ui.setCommandOutput(sb.toString());
    }
}
