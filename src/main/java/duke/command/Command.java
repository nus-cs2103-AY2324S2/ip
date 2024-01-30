package duke.command;

import duke.TaskList;
import duke.TextUi;
import duke.Storage;
import duke.DukeException;
public abstract class Command {
    /**
     * An abstract method to execute the command given
     * @param tasks A TaskList class that represents task list
     * @param ui A TextUi class that represents the ui
     * @param storage A Storage class which represents the storage of file
     */
    public abstract void execute(TaskList tasks, TextUi ui, Storage storage) throws DukeException;
    /**
     * An abstract method to indicate whether to exit the program
     */
    public abstract boolean isExit();
}
