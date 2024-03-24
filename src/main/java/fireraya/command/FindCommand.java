package fireraya.command;

import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;
import fireraya.task.Task;

import java.util.ArrayList;

/**
 * Class to handle a Find Command in the program.
 *
 * This class is extended from the Command superclass.
 */
public class FindCommand extends Command{
    private String keyword;

    /**
     * Constructor for the Find Command.
     *
     * @param key String keyword to find in the TaskList.
     */
    public FindCommand(String key) {
        this.keyword = key;
    }

    /**
     * Overrides the format to execute the command.
     *
     * @param tasks the Tasklist of program.
     * @param ui the Ui of the program.
     * @param storage the storage of the program.
     * @return String representing the message to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert this.isExit() == false;
        ArrayList<Task> filtered = tasks.filter(keyword);
        String a = ui.print("Here are some results I found in your list!\n");
        String b = ui.listTasks(filtered);
        return  a + b;
    }
}
