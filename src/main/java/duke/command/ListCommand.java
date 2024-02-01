package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;


/**
 * Represents the command to list out tasks.
 */
public class ListCommand extends Command {


    /**
     * Executes the list command.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listTasks(taskList);
    }
}
