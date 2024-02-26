package yarr.command;

import yarr.Parser;
import yarr.Storage;
import yarr.TaskList;
import yarr.Ui;


/**
 * Represents a command to get help for a specific command or to display a list of commands.
 * This class extends the Command class and overrides the execute method to perform
 * the help operation.
 */
public class HelpCommand extends Command {
    /**
     * The command to get help for.
     */
    private String input;

    /**
     * Constructs new HelpCommand object with no specified command to get help for.
     */
    public HelpCommand() {
        this.input = "";
    }

    /**
     * Constructs new HelpCommand object with a specified command to get help for.
     *
     * @param input the command to get help for
     */
    public HelpCommand(String input) {
        this.input = input;
    }

    /**
     * Adds specified task to the task list and saves to hard disk.
     *
     * @param tasks The TaskList object on which the command will operate
     * @param storage The Storage object that will read and write to files
     * @param ui The Ui object that handles displaying messages
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        super.tasks = tasks;
        super.storage = storage;
        super.ui = ui;
        Parser.Instructions command = helperCommandMatch(this.input);
        switch (command) {
        case TODO:
            return ui.printMessage("Adds a todo task to the list.\n"
                    + "Format: todo <description>\n"
                    + "Aliases: 'td'\n"
                    + "Examples: 'todo read book', 'td read book'");
        case DEADLINE:
            return ui.printMessage("Adds a deadline task to the list.\n"
                    + "Format: deadline <description> /by <date> <time>\n"
                    + "Date/Time Formats:\n"
                    + "- 'dd/MM/yyyy HHmm' (01/01/2024 2300)\n"
                    + "- 'dd-MM-yyyy HHmm' (01-01-2024 2300)\n"
                    + "- 'ddMMyyyy HHmm' (01012024 2300\n"
                    + "Aliases:\n"
                    + "- For 'deadline': 'dl'\n"
                    + "- For '/by': '/b', '/at' or '/a'\n"
                    + "Examples:\n"
                    + "- 'deadline return book /by 12/12/2019 1800'\n"
                    + "- 'dl return book /b 12-12-2019 1800'");
        case EVENT:
            return ui.printMessage("Adds an event task to the list.\n"
                    + "Format: event <description> /from <date> <time> /to <date> <time>\n"
                    + "Date/Time Formats:\n"
                    + "- 'dd/MM/yyyy HHmm' (01/01/2024 2300)\n"
                    + "- 'dd-MM-yyyy HHmm' (01-01-2024 2300)\n"
                    + "- 'ddMMyyyy HHmm' (01012024 2300\n"
                    + "Aliases:\n"
                    + "- For 'event': 'ev'\n"
                    + "- For '/from': '/f', '/start' or '/s'\n"
                    + "- For '/to': '/t', '/end', '/e'\n"
                    + "Examples:\n"
                    + "- 'event project meeting /from 12/12/2019 1800 /to 12/12/2019 2000'\n"
                    + "- 'ev project meeting /f 12-12-2019 1800 /t 12-12-2019 2000'");
        case LIST:
            return ui.printMessage("Lists all tasks in the list.\n"
                    + "Format: list\n"
                    + "Aliases: 'l' or 'ls'");
        case MARK:
            return ui.printMessage("Marks a task as done.\n"
                    + "Format: mark <index>\n"
                    + "Aliases: 'm' or 'done'\n"
                    + "Examples: 'mark 1', 'm 1', 'done 1'");
        case UNMARK:
            return ui.printMessage("Unmarks a task as not done.\n"
                    + "Format: unmark <index>\n"
                    + "Aliases: 'u', 'um' or 'undone'\n"
                    + "Examples: 'unmark 1', 'u 1', 'um 1', 'undone 1'");
        case DELETE:
            return ui.printMessage("Deletes a task from the list.\n"
                    + "Format: delete <index>\n"
                    + "Aliases: 'del' or 'rm'\n"
                    + "Examples: 'delete 1', 'del 1', 'rm 1'");
        case FIND:
            return ui.printMessage("Finds tasks containing the specified keyword.\n"
                    + "Format: find <keyword>\n"
                    + "Aliases: 'f' or 'search'\n"
                    + "Examples: 'find book', 'f book', 'search book'");
        case BYE:
            return ui.printMessage("Exits the program.\n"
                    + "Format: bye\n"
                    + "Aliases: 'exit' or 'quit' instead of 'bye'");
        default:
            if (input == "") {
                return ui.printMessage("Here be ye valid commands:\n"
                        + "1. todo <description>\n"
                        + "2. deadline <description> /by <date> <time>\n"
                        + "3. event <description> /from <date> <time> /to <date> <time>\n"
                        + "4. list\n"
                        + "5. mark <index>\n"
                        + "6. unmark <index>\n"
                        + "7. delete <index>\n"
                        + "8. find <keyword>\n"
                        + "9. help\n"
                        + "10. bye\n"
                        + "Type 'help <command>' to find out more about a specific command.");
            } else {
                return ui.printMessage("Avast ye! I cannot fathom that command."
                        + "Type 'help' to see a list of commands.\n"
                        + "Type 'help <command>' to find out more about a specific command.");
            }
        }
    }

    /**
     * Matches the input to a command and returns the command.
     * Doesn't match help commands and instead exits to a default case.
     *
     * @param input the input to be matched to a command
     * @return the command that matches the input
     */
    private Parser.Instructions helperCommandMatch(String input) {
        if (input.equals("")) {
            return Parser.Instructions.HELP;
        }
        for (Parser.Instructions i : Parser.Instructions.values()) {
            for (String s : i.getPatterns()) {
                if (s.contains(input)) {
                    return i;
                }
            }
        }
        return Parser.Instructions.HELP;
    }
}
