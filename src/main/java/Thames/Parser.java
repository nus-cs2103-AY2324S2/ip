package Thames;

import Thames.command.AddCommand;
import Thames.command.FindCommand;
import Thames.command.DeleteCommand;
import Thames.command.ExitCommand;
import Thames.command.EditCommand;
import Thames.command.Command;
import Thames.command.ListCommand;
import Thames.task.ToDo;
import Thames.task.Deadline;
import Thames.task.Event;

/**
 * Class that makes sense of the user input.
 */
public class Parser {
    /**
     * Makes sense of the user input to return Command object.
     *
     * @param fullCommand User input.
     * @return Command that contains the properties of user input.
     * @throws ThamesException If user input is invalid.
     */
    static Command parse(String fullCommand) throws ThamesException {
        String[] split = fullCommand.trim().split(" ", 2);
        String input = split[0];

        switch(input.toLowerCase()) {
        case "todo":
            if (split.length != 2) {
                throw new ThamesException("Please provide your Todo task in the following format: \n" +
                        "Todo <task name>\n");
            }
            return new AddCommand(new ToDo(split[1].trim()));
        case "deadline":
            if (split.length != 2) {
                throw new ThamesException("Please provide your Deadline task in the following format: \n" +
                        "Deadline <task name> /by <yyyy-mm-dd>\n");
            }
            String[] split1 = split[1].split("/by");
            if (split1.length != 2) {
                throw new ThamesException("Please provide your Deadline task in the following format: \n" +
                        "Deadline <task name> /by <yyyy-mm-dd>\n");
            }
            String desc = split1[0].trim();
            String by = split1[1].trim();
            if (desc.length() == 0 || by.length() == 0) {
                throw new ThamesException("Please provide your Deadline task in the following format: \n" +
                        "Deadline <task name> /by <yyyy-mm-dd>\n");
            }
            return new AddCommand(new Deadline(desc, by));
        case "event":
            String[] split2 = split[1].split("/from");
            if (split2.length != 2) {
                throw new ThamesException("Please provide your Event task in the following format: \n" +
                        "Event <task name> /by <yyyy-mm-dd>\n");
            }
            String[] split3 = split2[1].split("/to");
            if (split3.length != 2) {
                throw new ThamesException("Please provide your Event task in the following format: \n" +
                        "Event <task name> /by <yyyy-mm-dd>\n");
            }
            desc = split2[0].trim();
            String from = split3[0].trim();
            String to = split3[1].trim();
            if (desc.length() == 0 || from.length() == 0 || to.length() == 0) {
                throw new ThamesException("Please provide your Event task in the following format: \n" +
                        "Event <task name> /by <yyyy-mm-dd>\n");
            }
            return new AddCommand(new Event(desc, from, to));
        case "mark":
            return new EditCommand(true, Integer.parseInt(split[1].trim()) - 1);
        case "unmark":
            return new EditCommand(false, Integer.parseInt(split[1].trim()) - 1);
        case "delete":
            return new DeleteCommand(Integer.parseInt(split[1].trim()) - 1);
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "find":
            if (split.length != 2) {
                throw new ThamesException("Please input the keyword for me to find!");
            }
            return new FindCommand(split[1].trim());

        }
        throw new ThamesException("Sorry, I do not understand what that means.");
    }
}
