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
     */
    public abstract String execute(Storage s, TaskList t, Ui u) throws BelleException;

    /**
     * Returns true if the program should exit
     * after this belle.belle.command and false otherwise.
     *
     * @return Boolean value to exit program.
     */
    public abstract boolean isExit();
}
