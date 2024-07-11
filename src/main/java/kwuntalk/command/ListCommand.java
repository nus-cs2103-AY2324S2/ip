package kwuntalk.command;

import kwuntalk.Storage;
import kwuntalk.TaskList;
import kwuntalk.Ui;


/**
 * Represents the command to list out tasks.
 */
public class ListCommand extends Command {


    /**
     * Executes the list command and generates the response.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @return The reply to the user's input.
     */
    @Override
    public String generateReply(TaskList taskList, Ui ui, Storage storage) {
        return ui.listTasks(taskList);
    }
}
