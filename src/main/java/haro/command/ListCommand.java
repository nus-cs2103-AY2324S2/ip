package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;

/**
 * The ListCommand class represents a command to display the list of tasks.
 * It extends the base Command class and implements the execute method to print the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand instance.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the command by printing the list of tasks.
     *
     * @param taskList TaskList to be displayed
     * @param ui       Ui for displaying messages related to command execution
     * @param storage  Storage for saving and loading task data
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printList(taskList);
    }

    /**
     * Checks if the given object is equal to this ListCommand.
     *
     * @param o Object to be compared
     * @return true if the object is an instance of ListCommand; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o instanceof ListCommand) {
            return true;
        }

        return false;
    }
}
