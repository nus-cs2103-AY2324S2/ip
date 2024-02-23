package duke.command;

import duke.command.bye.ByeCommand;
import duke.command.sort.SortCommand;
import duke.command.taskNo.DeleteCommand;
import duke.command.find.FindCommand;
import duke.command.list.ListCommand;
import duke.command.taskNo.MarkCommand;
import duke.command.taskNo.UnmarkCommand;
import duke.command.task.DeadlineCommand;
import duke.command.task.EventCommand;
import duke.command.task.ToDoCommand;

import java.util.EnumSet;

/**
 * A collection of Command Enum objects to store available commands and its signatures.
 */
public enum CommandEnum {
    TODO(ToDoCommand.COMMAND, "todo <task_name>"),
    DEADLINE(DeadlineCommand.COMMAND, "deadline <task_name> /by <due_date>"),
    EVENT(EventCommand.COMMAND,"event <task_name> /from <start_date> /to <end_date"),
    LIST(ListCommand.COMMAND, "list"),
    MARK (MarkCommand.COMMAND, "mark <task_number>"),
    UNMARK(UnmarkCommand.COMMAND, "unmark <task_number>"),
    DELETE(DeleteCommand.COMMAND, "delete <task_number>"),
    FIND(FindCommand.COMMAND, "find <keyword>"),
    SORT(SortCommand.COMMAND, "sort <type>"),
    BYE(ByeCommand.COMMAND, "bye");

    public final String COMMAND_NAME; // the command keyword
    public final String COMMAND_SIGNATURE; // the command format

    /**
     * Creates a command with a keyword and its format
     * @param commandName
     * @param commandSignature
     */
    private CommandEnum(String commandName, String commandSignature) {
        this.COMMAND_NAME = commandName;
        this.COMMAND_SIGNATURE = commandSignature;
    }

    public static String getCommandEnumList() {
        StringBuilder list = new StringBuilder();
        for (CommandEnum c: EnumSet.allOf(CommandEnum.class)) {
            list.append(c.COMMAND_SIGNATURE).append("\n");
        }

        return list.toString();
    }

}
