package duke.commands;

import duke.exceptions.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;



/**
 * Abstract class to be inherited by all commands
 */
public abstract class Command {
    /**
     * abstract method for inheriting class to implement in order to
     * specify their individual behaviors
     * @param tasks Task Object holding all user-task
     * @param ui UI that manages user interactions
     * @param s Storage that manages User file system
     * @return boolean for DUKE to determine if it to exit the program
     * @throws DukeException exceptions specific to DUKE app
     */
    public abstract boolean execute(TaskList tasks, UI ui, Storage s) throws DukeException;

    /**
     * abstract method to return String to be displayed on GUI
     * @param tasks Task Object holding all user-task
     * @param ui UI that manages user interactions
     * @param storage Storage that manages User file system
     * @return String to be displayed on GUI
     * @throws DukeException exceptions specific to DUKE app
     */
    public abstract String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException;
}

