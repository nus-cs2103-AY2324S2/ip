package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

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
     * @param storage  the storage
     * @throws DukeException the duke exception
     */
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        String taskoutput = taskList.printOutput();
        sb.append(taskoutput);
        return sb.toString();
    }
}
