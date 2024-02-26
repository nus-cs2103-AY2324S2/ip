package seiki.commands;

import seiki.data.TaskList;
import seiki.data.exception.SeikiException;
import seiki.storage.Storage;
import seiki.ui.Ui;

/**
 * Represents the 'help' command.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Displays chatbot usage instructions.\n"
            + "Example: " + COMMAND_WORD;
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws SeikiException {
        StringBuilder sb = new StringBuilder();
        sb.append("\u2794 " + ByeCommand.COMMAND_USAGE + "\n");;
        sb.append("\u2794 " + DeadlineCommand.COMMAND_USAGE + "\n");
        sb.append("\u2794 " + DeleteCommand.COMMAND_USAGE + "\n");
        sb.append("\u2794 " + EventCommand.COMMAND_USAGE + "\n");
        sb.append("\u2794 " + FindCommand.COMMAND_USAGE + "\n");
        sb.append("\u2794 " + HelpCommand.COMMAND_USAGE + "\n");
        sb.append("\u2794 " + ListCommand.COMMAND_USAGE + "\n");
        sb.append("\u2794 " + MarkCommand.COMMAND_USAGE + "\n");
        sb.append("\u2794 " + ToDoCommand.COMMAND_USAGE + "\n");
        sb.append("\u2794 " + UnmarkCommand.COMMAND_USAGE + "\n");
        return ui.showToUser(sb.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
