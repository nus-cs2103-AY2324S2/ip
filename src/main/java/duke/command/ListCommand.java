package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        //do nothing
    }

    /**
     * Method that lists the task from the current task list within the program and on the hard disk.
     *
     * @param tasks The TaskList object on which the command will operate
     * @param storage The Storage object that will read and write to files
     * @param ui The Ui object that handles displaying messages
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        super.tasks = tasks;
        super.storage = storage;
        super.ui = ui;
        super.ui.printDivider();
        System.out.println("Behold, yer roster of endeavors!");
        super.tasks.listTasks();
        super.ui.printDivider();
    }
}
