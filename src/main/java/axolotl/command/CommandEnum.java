package axolotl.command;

import axolotl.command.bye.ByeCommand;
import axolotl.command.find.FindCommand;
import axolotl.command.list.ListCommand;
import axolotl.command.sort.SortCommand;
import axolotl.command.task.DeadlineCommand;
import axolotl.command.task.EventCommand;
import axolotl.command.task.ToDoCommand;
import axolotl.command.taskNo.DeleteCommand;
import axolotl.command.taskNo.MarkCommand;
import axolotl.command.taskNo.UnmarkCommand;

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
