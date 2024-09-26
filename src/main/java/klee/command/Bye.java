package klee.command;

import klee.Storage;
import klee.TaskList;
import klee.Ui;

/**
 * Represents the command for the user to end the program.
 */
public class Bye extends Command {
    public Bye() {}

    /**
     * Invoke the UI to say goodbye to the user.
     *
     * @param ui
     * @param storage
     * @param tasks
     */
    @Override
    public void runCommand(Ui ui, Storage storage, TaskList tasks) {
        ui.showBye();
    }

    /**
     * Check if the obj is an instance of class Bye.
     *
     * @param obj
     * @return If the obj is an instance of class Bye.
     */
    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == Bye.class;
    }
}
