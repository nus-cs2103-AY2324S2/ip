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
        if (userInput.isBlank()) {
            throw new FloofyException("Remember, your wish is my command! Try keying in a command! :)");
        }
        if (isMarkCommand(userInput)) {
            return parseMarkCommand(userInput);
        } else if (isUnmarkCommand(userInput)) {
            return parseUnmarkCommand(userInput);
        } else if (isFindCommand(userInput)) {
            return parseFindCommand(userInput);
        } else if (isTodoCommand(userInput)) {
            return parseTodoCommand(userInput);
        } else if (isDeadlineCommand(userInput)) {
            return parseDeadlineCommand(userInput);
        } else if (isEventCommand(userInput)) {
            return parseEventCommand(userInput);
        } else if (isDeleteCommand(userInput)) {
            return parseDeleteCommand(userInput);
        } else if (isListCommand(userInput)) {
            return parseListCommand(userInput);
        } else if (isByeCommand(userInput)) {
            return parseByeCommand(userInput);
        } else {
            throw new FloofyException("To add a task, please start with any of these commands: " +
                    "'todo', 'deadline' or 'event'!");
        }
    }

    /**
     * Parses the "mark" command and returns the parsed input.
     *
     * @param userInput The user input to be parsed.
     * @return The parsed input as an array with the "mark" command and corresponding task index to mark.
     * @throws FloofyException If the user input is not in the valid format of "mark x", where x is an integer.
     */
    public String[] parseMarkCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[2];
        int minimumLength = getMinLengthOfCommand(userInput);
        if (userInput.length() < minimumLength) {
            throw new FloofyException("To mark a task, type 'mark ' followed by the task number! e.g. 'mark 1':)");
        }
        parsedInput[0] = "mark";
        parsedInput[1] = userInput.substring(taskNumberIdx(userInput));
        assert !isNotNumber(parsedInput[1]) : "Task number should be a number!";
        if (isNotNumber(parsedInput[1])) {
            throw new FloofyException("To mark a task, type 'mark ' followed by the task NUMBER! e.g. 'mark 1':)");
        }
        return parsedInput;
    }

    /**
     * Parses the "unmark" command and returns the parsed input.
     *
     * @param userInput The user input to be parsed.
     * @return The parsed input as an array with the "unmark" command and corresponding task index to unmark.
     * @throws FloofyException If the user input is not in the valid format of "unmark x", where x is an integer.
     */
    public String[] parseUnmarkCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[2];
        int minimumLength = getMinLengthOfCommand(userInput);
        if (userInput.length() < minimumLength) {
            throw new FloofyException("To unmark a task, " +
                    "type 'unmark ' followed by the task number! e.g. 'unmark 1':)");
        }
        parsedInput[0] = "unmark";
        parsedInput[1] = userInput.substring(taskNumberIdx(userInput));
        if (isNotNumber(parsedInput[1])) {
            throw new FloofyException("To unmark a task, " +
                    "type 'unmark ' followed by the task NUMBER! e.g. 'unmark 1':)");
        }
        return parsedInput;
    }

    /**
     * Parses the "find" command and returns the parsed input.
     *
     * @param userInput The user input to be parsed.
     * @return The parsed input as an array with the "find" command and the corresponding keyword to find.
     * @throws FloofyException If the user did not input a keyword to find.
     */
    public String[] parseFindCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[2];
        int minimumLength = getMinLengthOfCommand(userInput);
        if (userInput.length() < minimumLength) {
            throw new FloofyException("To find a task, type 'find ' followed by the task to find! e.g. 'find book':)");
        }
        parsedInput[0] = "find";
        parsedInput[1] = userInput.substring(taskNumberIdx(userInput));
        return parsedInput;
    }

    /**
     * Parses the "todo" command and returns the parsed input.
     *
     * @param userInput The user input to be parsed.
     * @return The parsed input as an array with the "todo" command and the corresponding task description.
     * @throws FloofyException If the user did not input a task description.
     */
    public String[] parseTodoCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[2];
        int minimumLength = getMinLengthOfCommand(userInput);
        if (userInput.length() < minimumLength) {
            throw new FloofyException("Remember to add an actual task. Try again!");
        }
        parsedInput[0] = "todo";
        parsedInput[1] = userInput.substring(descriptionIdx(userInput));
        return parsedInput;
    }

    /**
     * Parses the "deadline" command and returns the parsed input.
     *
     * @param userInput The user input to be parsed.
     * @return The parsed input as an array with the "deadline" command,
     *         the corresponding task description and the deadline date.
     * @throws FloofyException If the user did not input a task description or a deadline date.
     */
    public String[] parseDeadlineCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[3];
        int minimumLength = getMinLengthOfCommand(userInput);
        if (userInput.length() < minimumLength) {
            throw new FloofyException("Remember to add an actual task. Try again!");
        }
        if (!(userInput.contains("/by"))) {
            throw new FloofyException("Remember to state the deadline after a '/by'!");
        }
        parsedInput[0] = "deadline";
        parsedInput[1] = userInput.substring(descriptionIdx(userInput), byIdx(userInput) - 1);
        parsedInput[2] = userInput.substring(byIdx(userInput) + 4);
        return parsedInput;
    }

    /**
     * Parses the "event" command and returns the parsed input.
     *
     * @param userInput The user input to be parsed.
     * @return The parsed input as an array with the "event" command,
     *         the corresponding task description and the event start and end dates.
     * @throws FloofyException If the user did not input a task description or the event start and end dates
     *                         in the correct format.
     */
    public String[] parseEventCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[4];
        int minimumLength = getMinLengthOfCommand(userInput);
        if (userInput.length() < minimumLength) {
            throw new FloofyException("Remember to add an actual task. Try again!");
        }
        if (!(userInput.contains("/from"))) {
            throw new FloofyException("Remember to state the start of your event after a '/from'!");
        }
        if (!(userInput.contains("/to"))) {
            throw new FloofyException("Remember to state the end of your event after a '/to'!");
        }
        parsedInput[0] = "event";
        parsedInput[1] = userInput.substring(descriptionIdx(userInput), fromIdx(userInput) - 1);
        parsedInput[2] = userInput.substring(fromIdx(userInput) + 6, toIdx(userInput) - 1);
        parsedInput[3] = userInput.substring(toIdx(userInput) + 4);
        return parsedInput;
    }

    /**
     * Parses the "delete" command and returns the parsed input.
     *
     * @param userInput The user input to be parsed.
     * @return The parsed input as an array with the "delete" command and the corresponding task index to delete.
     * @throws FloofyException If the user input is not in the valid format of "delete x", where x is an integer.
     */
    public String[] parseDeleteCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[2];
        int minimumLength = getMinLengthOfCommand(userInput);
        if (userInput.length() < minimumLength) {
            throw new FloofyException("To delete a task, " +
                    "type 'delete ' followed by the task number! e.g. 'delete 1':)");
        }
        parsedInput[0] = "delete";
        parsedInput[1] = userInput.substring(descriptionIdx(userInput));
        if (isNotNumber(parsedInput[1])) {
            throw new FloofyException("To delete a task, " +
                    "type 'delete ' followed by the task NUMBER! e.g. 'delete 1':)");
        }
        return parsedInput;
    }

    /**
     * Parses the "list" command and returns the parsed input.
     *
     * @param userInput The user input to be parsed.
     * @return The parsed input as an array with the "list" command.
     * @throws FloofyException If the user input is invalid and not just "list".
     */
    public String[] parseListCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[1];
        if (userInput.length() > 4) {
            throw new FloofyException("To list your tasks, just type 'list' :)");
        }
        parsedInput[0] = "list";
        return parsedInput;
    }

    /**
     * Parses the "bye" command and returns the parsed input.
     *
     * @param userInput The user input to be parsed.
     * @return The parsed input as an array with the "bye" command.
     * @throws FloofyException If the user input is invalid and not just "bye".
     */
    public String[] parseByeCommand(String userInput) throws FloofyException {
        String[] parsedInput = new String[1];
        if (userInput.length() > 3) {
            throw new FloofyException("To exit, just type 'bye' :)");
        }
        parsedInput[0] = "bye";
        return parsedInput;
    }

    /**
     * Checks if the input is not a number.
     *
     * @param input The input to be checked.
     * @return True if the input is not a number, false otherwise.
     */
    public boolean isNotNumber(String input) {
        try {
            Integer.parseInt(input);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    // A list of helper functions to abstract details of the different steps of parsing.

    /**
     * Gets the first word of the user input.
     *
     * @param input The user input to be parsed.
     * @return The first word of the user input.
     */
    public String getFirstWord(String input) {
        return input.split(" ")[0];
    }

    /**
     * Gets the minimum length of the command.
     *
     * @param input The user input to be parsed.
     * @return The minimum length of the overall user input.
     */
    public int getMinLengthOfCommand(String input) {
        return getFirstWord(input).length() + 2;
    }

    public int descriptionIdx(String input) {
        return getFirstWord(input).length() + 1;
    }

    public int taskNumberIdx(String input) {
        return getFirstWord(input).length() + 1;
    }

    public int byIdx(String input) {
        return input.indexOf("/by");
    }

    public int fromIdx(String input) {
        return input.indexOf("/from");
    }

    public int toIdx(String input) {
        return input.indexOf("/to");
    }

    // A list of helper functions to interpret the user input command.

    public boolean isMarkCommand(String input) {
        String command = getFirstWord(input).toLowerCase();
        return command.startsWith("mark") || command.startsWith("mk") || command.startsWith("m");
    }

    public boolean isUnmarkCommand(String input) {
        String command = getFirstWord(input).toLowerCase();
        return command.startsWith("unmark") || command.startsWith("um") || command.startsWith("u");
    }

    public boolean isFindCommand(String input) {
        String command = getFirstWord(input).toLowerCase();
        return command.startsWith("find") || command.startsWith("f");
    }

    public boolean isTodoCommand(String input) {
        String command = getFirstWord(input).toLowerCase();
        return command.startsWith("todo") || command.startsWith("td") || command.startsWith("t");
    }

    public boolean isDeadlineCommand(String input) {
        String command = getFirstWord(input).toLowerCase();
        return command.startsWith("deadline") || command.startsWith("ddl") || command.startsWith("dln");
    }

    public boolean isEventCommand(String input) {
        String command = getFirstWord(input).toLowerCase();
        return command.startsWith("event") || command.startsWith("evt") ||
                command.startsWith("ev") || command.startsWith("e");
    }

    public boolean isDeleteCommand(String input) {
        String command = getFirstWord(input).toLowerCase();
        return command.startsWith("delete") || command.startsWith("del") || command.startsWith("dlt");
    }

    public boolean isListCommand(String input) {
        String command = getFirstWord(input).toLowerCase();
        return command.startsWith("list") || command.startsWith("ls") || command.startsWith("l");
    }

    public boolean isByeCommand(String input) {
        String command = getFirstWord(input).toLowerCase();
        return command.startsWith("bye") || command.startsWith("b");
    }
}
