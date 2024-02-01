package duke.commands;

import duke.utils.TaskList;
import duke.utils.Storage;
import duke.utils.Ui;

/**
 * This class implements the commmand for listing tasks in a tasklist.
 */
public class ListTaskCommand extends Command {

    /**
     * Creates a ListTaskCommand, sets isExit to false.
     */
    public ListTaskCommand() {
        super(false);
    }
    
    /** 
     * Executes list task command, uses ui to print out tasks in provided TaskList.
     * 
     * @param tasks the current list of tasks.
     * @param ui Ui object used by bot for printing information.
     * @param storage Storage object with save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList(ui);
    }
}
