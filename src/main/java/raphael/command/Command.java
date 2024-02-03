package raphael.command;

import raphael.exception.RaphaelException;
import raphael.task.TaskList;
import raphael.ui.Ui;
import raphael.storage.Storage;

public abstract class Command {
    public enum TYPE {
        BYE("bye"),
        TODO("todo [task]"),
        DEADLINE("deadline [task] /by [dd/MM/yyyy HHmm]"),
        EVENT("event [task] /from [dd/MM/yyyy HHmm] /to [dd/MM/yyyy HHmm]"),
        MARK("mark [index]"),
        UNMARK("unmark [index]"),
        DELETE("delete [index]"),
        LIST("list"),
        CHECK("check [dd/MM/yyyy HHmm]"),
        FIND("find [word]");
        private final String format;
        private TYPE(String format) {
            this.format = format;
        }
        public String getFormat() {
            return this.format;
        }
    }
    /**
     * Executes the current command. This method is made abstract so that the command subclasses must override it
     * and provide their own implementations.
     *
     * @param tasks the task list
     * @param ui the user interface object
     * @param storage the file I/O object
     * @throws raphael.exception.RaphaelException exception exclusive to Raphael
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws RaphaelException;

    /**
     * Returns a boolean value indicating if the current command is the exit command.
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
