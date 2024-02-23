package chingu.command;

import chingu.Storage;
import chingu.task.TaskList;
import chingu.Ui;

public class ListCommand extends Command {
    public ListCommand() {

    }

    /**
     * Executes the ListCommand by the user
     *
     * @param tasks that contains current list of tasks
     * @param ui that handles the Ui of the Chingu Program
     * @param storage that deals with loading or saving the file
     * @return the response of the Chingu program from the command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String Response = ui.announceListing();
        for (int i = 0; i < tasks.getSizeNumber(); i++) {
            int n = i + 1;
            Response = Response + n + ". " + tasks.getTask(i).toString() + "\n";
        }
        return Response;
    }

    /**
     * Checks if it is last command of the Program (Exit Command - "bye")
     *
     * @return boolean that indicate if it is the last command from user
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
