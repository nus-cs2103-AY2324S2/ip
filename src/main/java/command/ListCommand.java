package command;

import duke.Storage;
import duke.TaskList;

/**
 * Represents a command that lists all currently recorded tasks
 */
public class ListCommand extends Command {

    /**
     * Judges if another command is also a ListCommand.
     *
     * @param other Another command
     * @return True if another command is also ListCommand
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof ListCommand;
    }

    /**
     * Prints currently recorded tasks.
     *
     * @param storage Involved in file management
     * @param taskList Active during the execution of the program
     * @return A string that contains all the tasks
     */
    @Override
    public String execute(Storage storage, TaskList taskList) {
        return taskList.print();
    }
}
