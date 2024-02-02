
//
//public class Parser {
//    private String input;
//
//    public Parser(String input) {
//        this.input = input;
//    }
//
//    public Command parse() throws DukeException {
//        String[] inputParts = input.split(" ", 2); // Split input into command and argument
//
//        String commandStr = inputParts[0].toLowerCase();
//        String argument = inputParts.length > 1 ? inputParts[1].trim() : "";
//
//        switch (commandStr) {
//            case "bye":
//                return new Command(Command.CommandType.BYE, argument);
//            case "list":
//                return new Command(Command.CommandType.LIST, argument);
//            case "todo":
//                return new Command(Command.CommandType.TODO, argument);
//            case "deadline":
//                return new Command(Command.CommandType.DEADLINE, argument);
//            case "event":
//                return new Command(Command.CommandType.EVENT, argument);
//            case "delete":
//                return new Command(Command.CommandType.DELETE, argument);
//            case "mark":
//                return new Command(Command.CommandType.MARK, argument);
//            case "unmark":
//                return new Command(Command.CommandType.UNMARK, argument);
//            default:
//                throw new DukeException("I'm sorry, but I don't know what that means :-(");
//        }
//    }
//}
package duke;
/**
 * Represents a parser for processing user commands in the Duke program.
 */
public class Parser {
    private String input;

    /**
     * Constructs a Parser object with the specified input string.
     *
     * @param input The input string to be parsed.
     */
    public Parser(String input) {
        this.input = input;
    }


    /**
     * Parses the input string and returns the corresponding command.
     *
     * @return A Command object representing the parsed user command.
     * @throws DukeException If there is an error parsing the input.
     */
    public Command parse() throws DukeException {
        if (input.equalsIgnoreCase("bye")) {
            return new Command(Command.CommandType.BYE, null);
        } else if (input.equalsIgnoreCase("list")) {
            return new Command(Command.CommandType.LIST, null);
        } else if (input.startsWith("todo")) {
            // Check if the todo description is empty
            String description = input.substring(4).trim();
            if (description.isEmpty()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            return new Command(Command.CommandType.TODO, description);
        } else if (input.startsWith("deadline")) {
            return new Command(Command.CommandType.DEADLINE, input.substring(9).trim());
        } else if (input.startsWith("event")) {
            return new Command(Command.CommandType.EVENT, input.substring(6).trim());
        } else if (input.startsWith("delete")) {
            return new Command(Command.CommandType.DELETE, input.substring(7).trim());
        } else if (input.startsWith("mark")) {
            return new Command(Command.CommandType.MARK, input.substring(5).trim());
        } else if (input.startsWith("unmark")) {
            return new Command(Command.CommandType.UNMARK, input.substring(7).trim());
        } else if (input.startsWith("find")) {
        return parseFindCommand(input);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private Command parseFindCommand(String input) throws DukeException {
        String keyword = input.substring(4).trim();
        if (keyword.isEmpty()) {
            throw new DukeException("OOPS!!! The keyword for find cannot be empty.");
        }
        return new Command(Command.CommandType.FIND, keyword);
    }
}
