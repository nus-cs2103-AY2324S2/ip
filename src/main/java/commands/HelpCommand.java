package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 *  Command that signifies asking for help in Blawg application
 *  Displays all possible commands that Blawg will execute
 */
public class HelpCommand extends Command {
    private final Command command;

    public HelpCommand(Command command) {
        this.command = command;
    }

    public HelpCommand() {
        this.command = null;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.command == null) {
            return this.showUsage();
        } else {
            return this.command.showUsage();
        }
    }

    @Override
    public String showUsage() {
        return "Possible commands are: "
                + "bye, list, mark, unmark, todo, deadline, event, and delete";
    }
}
