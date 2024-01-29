package Klee.command;

import Klee.KleeException;

import Klee.Ui;
import Klee.Storage;
import Klee.TaskList;

/**
 * Represents a command that Klee can run.
 */
public abstract class Command {
    /**
     * To execute the command given by the user.
     *
     * @param ui
     * @param storage
     * @param tasks
     * @throws KleeException
     */
    public abstract void runCommand(Ui ui, Storage storage, TaskList tasks) throws KleeException;
}