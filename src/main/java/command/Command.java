package command;

import run.Storage;
import run.TaskList;
import run.Ui;
import others.BelleException;

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
    public abstract void execute(Storage s, TaskList t, Ui u) throws BelleException;

    /**
     * Returns true if the program should exit
     * after this command and false otherwise.
     *
     * @return Boolean value to exit program.
     */
    public abstract boolean isExit();
}