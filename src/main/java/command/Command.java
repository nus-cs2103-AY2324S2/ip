package command;

import exceptions.DukeException;

import task.TaskList;

import utilities.Storage;
import utilities.Ui;

public abstract class Command {
    /**
     * Variable to tell the program whether to exit or not.
     */
    private boolean isExit;

    /**
     * Command class constructor.
     * @param isExit Variable to tell the program whether to exit or not.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Method to tell the program whether to exit or not.
     * @return A boolean value.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes the task process.
     * @param taskList The task list that the command is applied to.
     * @param storage The storage that the task list is stored in after the command is applied.
     * @param ui Provides corresponding user output based on whether the process is successful or not.
     * @throws DukeException The exception thrown when the process is unsuccessful.
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException;
}
