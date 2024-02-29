package goblin.command;

import goblin.Storage;
import goblin.Ui;
import goblin.TaskList;
import goblin.exception.OrkException;

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
    public void execute(TaskList list, Ui ui, Storage storage) {
        System.out.println("\t" + "Read it yourself.");
        for (int i = 0; i < TaskList.list.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + TaskList.list.get(i).notPrint());
        }
    }
}
