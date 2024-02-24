package floofy;

/**
 * Represents a parser that parses user commands.
 */
public class Parser {

    /**
     * Constructs a new object of the Parser class.
     */
    public Parser() {

    }

    /**
     * Parses the user input and returns the parsed input.
     *
     * @param userInput The user input to be parsed.
     * @return The parsed input.
     * @throws FloofyException If the user input is invalid.
     */
    public String[] parse(String userInput) throws FloofyException {
        if (userInput.startsWith("mark")) {
            return parseMarkCommand(userInput);
        } else if (userInput.startsWith("unmark")) {
            return parseUnmarkCommand(userInput);
        } else if (userInput.startsWith("find")) {
            return parseFindCommand(userInput);
        } else if (userInput.startsWith("todo")) {
            return parseTodoCommand(userInput);
        } else if (userInput.startsWith("deadline")) {
            return parseDeadlineCommand(userInput);
        } else if (userInput.startsWith("event")) {
            return parseEventCommand(userInput);
        } else if (userInput.startsWith("delete")) {
            return parseDeleteCommand(userInput);
        } else if (userInput.startsWith("list")) {
            return parseListCommand(userInput);
        } else if (userInput.startsWith("bye")) {
            return parseByeCommand(userInput);
        } else {
            throw new FloofyException("To add a task, please start with any of these commands: 'todo', 'deadline' or 'event'!");
        }
    }

    public String[] parseMarkCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[2];
        if (userInput.length() != 6) {
            throw new FloofyException("To mark a task, type 'mark ' followed by the task number! e.g. 'mark 1':)");
        }
        parsedInput[0] = "mark";
        parsedInput[1] = userInput.substring(5);
        return parsedInput;
    }

    public String[] parseUnmarkCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[2];
        if (userInput.length() != 8) {
            throw new FloofyException("To unmark a task, type 'unmark ' followed by the task number! e.g. 'unmark 1':)");
        }
        parsedInput[0] = "unmark";
        parsedInput[1] = userInput.substring(7);
        return parsedInput;
    }

    public String[] parseFindCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[2];
        if (userInput.length() < 6) {
            throw new FloofyException("To find a task, type 'find ' followed by the task to find! e.g. 'find book':)");
        }
        parsedInput[0] = "find";
        parsedInput[1] = userInput.substring(5);
        return parsedInput;
    }

    public String[] parseTodoCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[2];
        if (userInput.length() < 6) {
            throw new FloofyException("Remember to add an actual task. Try again!");
        }
        parsedInput[0] = "todo";
        parsedInput[1] = userInput.substring(5);
        return parsedInput;
    }

    public String[] parseDeadlineCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[3];
        if (userInput.length() < 10) {
            throw new FloofyException("Remember to add an actual task. Try again!");
        }
        if (!(userInput.contains("/by"))) {
            throw new FloofyException("Remember to state the deadline after a '/by'!");
        }
        // get description
        parsedInput[0] = "deadline";
        parsedInput[1] = userInput.substring(9, userInput.indexOf("/by") - 1);
        // get date, but lacking parsed date
        parsedInput[2] = userInput.substring(userInput.indexOf("/by") + 4);
        return parsedInput;
    }

    public String[] parseEventCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[4];
        if (userInput.length() < 7) {
            throw new FloofyException("Remember to add an actual task. Try again!");
        }
        if (!(userInput.contains("/from"))) {
            throw new FloofyException("Remember to state the start of your event after a '/from'!");
        }
        if (!(userInput.contains("/to"))) {
            throw new FloofyException("Remember to state the end of your event after a '/to'!");
        }
        parsedInput[0] = "event";
        parsedInput[1] = userInput.substring(6, userInput.indexOf("/from") - 1);
        parsedInput[2] = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
        parsedInput[3] = userInput.substring(userInput.indexOf("/to") + 4);
        return parsedInput;
    }

    public String[] parseDeleteCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[2];
        if (userInput.length() != 8) {
            throw new FloofyException("To delete a task, type 'delete ' followed by the task number! e.g. 'delete 1':)");
        }
        parsedInput[0] = "delete";
        parsedInput[1] = userInput.substring(7);
        return parsedInput;
    }

    public String[] parseListCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[1];
        if (userInput.length() > 4) {
            throw new FloofyException("To list your tasks, just type 'list' :)");
        }
        parsedInput[0] = "list";
        return parsedInput;
    }

    public String[] parseByeCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[1];
        if (userInput.length() > 3) {
            throw new FloofyException("To exit, just type 'bye' :)");
        }
        parsedInput[0] = "bye";
        return parsedInput;
    }
}
