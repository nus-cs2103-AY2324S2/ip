package goblin.command;

import goblin.Storage;
import goblin.Ui;
import goblin.TaskList;

public class ListCommand extends Command {
    /**
     * create a new ListCommand
     */
    public ListCommand() {
        super();
    }

    /**
     * list out the tasks in the list
     * @param list the list of tasks
     * @param ui handle ui
     * @param storage handle storage
     */
    public String execute(TaskList list, Ui ui, Storage storage) {
        String message = "\t" + "Here are the tasks.";
        for (int i = 0; i < TaskList.list.size(); i++) {
            message = message + "\n" + "\t" + (i + 1) + "." + TaskList.list.get(i).notPrint();
        }
        return message;
    }
}
