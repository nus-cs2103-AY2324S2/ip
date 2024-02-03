/**
 * The ExitCommand class represents a command to exit.
 * It is a subclass of the Command class.
 */
package duke;

import duke.task.TaskList;

public class ExitCommand extends Command {

    /**
     * Executes the exit command, closing the application and saving tasks if necessary.
     *
     * @param taskList   The task list (not used in this command).
     * @param ui         The user interface for displaying messages.
     * @param userInput  The user input specifying the exit command.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
        ui.showGoodbye();
        Storage.saveTasks(taskList.getTasks());
        ui.closeScanner();
    }
}
