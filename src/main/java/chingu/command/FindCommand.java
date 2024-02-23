package chingu.command;

import chingu.Storage;
import chingu.task.Task;
import chingu.task.TaskList;
import chingu.Ui;

/**
 * This class deals with the FindCommand from the user
 */
public class FindCommand extends Command{

    private String keyword;
    private int count;

    public FindCommand(String keyword) {
        this.keyword = keyword;
        this.count = 1;
    }

    /**
     * Executes the FindCommand by the user
     *
     * @param tasks that contains current list of tasks
     * @param ui that handles the Ui of the Chingu Program
     * @param storage that deals with loading or saving the file
     * @return the response of the Chingu program from the command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String Response = ui.announceFinding();
        for (int i = 0; i < tasks.getSizeNumber(); i++) {
            Task task_now = tasks.getTask(i);
            String counter = count + ". ";
            if (task_now.findingKeyword(keyword)) {
                Response = Response + counter + task_now.toString() + "\n";
                count+=1;
            }
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
