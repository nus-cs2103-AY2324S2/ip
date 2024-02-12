package harper.commands;

import harper.utils.Storage;
import harper.utils.TaskList;
import harper.utils.Ui;

//CHECKSTYLE.OFF: MissingJavadocType
public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Executes different type of command.
     *
     * @param taskList utilities.TaskList to be operated.
     * @param ui utilities.Ui that handles input and output.
     * @param storage utilities.Storage that handles operation with hard disk.
     * @return Message of executing the command.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
