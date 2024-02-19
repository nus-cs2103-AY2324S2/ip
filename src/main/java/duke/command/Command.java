package duke.command;

import duke.others.BelleException;
import duke.run.Storage;
import duke.run.TaskList;
import duke.run.Ui;

/**
 * Performs different functions based on type of command.
 */
public abstract class Command {

    /**
     * Runs the command to complete its
     * specific function.
     *
     * @param s Storage containing data of
     *          previous program.
     * @param t Tasklist of program.
     * @param u Ui that handles user interactions.
     * @return Print statement for each specific
     *         command.
     */
    public abstract String execute(Storage s, TaskList t, Ui u) throws BelleException;

}
