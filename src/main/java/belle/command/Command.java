package belle.command;

import belle.others.BelleException;
import belle.run.Storage;
import belle.run.TaskList;
import belle.run.Ui;

/**
 * Performs different functions based on type of command.
 */
public abstract class Command {
    /**
     * Runs the command to complete its
     * specific function.
     *
     * @param storage Storage containing data of
     *          previous program.
     * @param taskList Tasklist of program.
     * @param ui Ui that handles user interactions.
     * @return Print statement for each specific
     *         command.
     * @throws BelleException if there is an
     *         exception related to belle.
     */
    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws BelleException;
}
