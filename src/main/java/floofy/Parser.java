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
        String[] parsedInput = new String[4];
        if (userInput.startsWith("mark")) {
            if (userInput.length() != 6) {
                throw new FloofyException("To mark a task, type 'mark ' followed by the task number! e.g. 'mark 1':)");
            }
            parsedInput[0] = "mark";
            parsedInput[1] = userInput.substring(5);
            return parsedInput;
        } else if (userInput.startsWith("unmark")) {
            if (userInput.length() != 8) {
                throw new FloofyException("To unmark a task, type 'unmark ' followed by the task number! e.g. 'unmark 1':)");
            }
            parsedInput[0] = "unmark";
            parsedInput[1] = userInput.substring(7);
            return parsedInput;
        } else if (userInput.startsWith("find")) {
            if (userInput.length() < 6) {
                throw new FloofyException("To find a task, type 'find ' followed by the task to find! e.g. 'find book':)");
            }
            parsedInput[0] = "find";
            parsedInput[1] = userInput.substring(5);
            return parsedInput;
        } else if (userInput.startsWith("todo")) {
            parsedInput[0] = "todo";
            if (userInput.length() < 6) {
                throw new FloofyException("Remember to add an actual task. Try again!");
            }
            parsedInput[1] = userInput.substring(5);
            return parsedInput;
        } else if (userInput.startsWith("deadline")) {
            parsedInput[0] = "deadline";
            if (userInput.length() < 10) {
                throw new FloofyException("Remember to add an actual task. Try again!");
            }
            if (!(userInput.contains("/by"))) {
                throw new FloofyException("Remember to state the deadline after a '/by'!");
            }
            // get description
            parsedInput[1] = userInput.substring(9, userInput.indexOf("/by") - 1);
            // get date, but lacking parsed date
            parsedInput[2] = userInput.substring(userInput.indexOf("/by") + 4);
            return parsedInput;
        } else if (userInput.startsWith("event")) {
            parsedInput[0] = "event";
            if (userInput.length() < 7) {
                throw new FloofyException("Remember to add an actual task. Try again!");
            }
            if (!(userInput.contains("/from"))) {
                throw new FloofyException("Remember to state the start of your event after a '/from'!");
            }
            if (!(userInput.contains("/to"))) {
                throw new FloofyException("Remember to state the end of your event after a '/to'!");
            }
            parsedInput[1] = userInput.substring(6, userInput.indexOf("/from") - 1);
            parsedInput[2] = userInput.substring(userInput.indexOf("/from") + 6, userInput.indexOf("/to") - 1);
            parsedInput[3] = userInput.substring(userInput.indexOf("/to") + 4);
            return parsedInput;
        } else if (userInput.startsWith("delete")) {
            if (userInput.length() != 8) {
                throw new FloofyException("To delete a task, type 'delete ' followed by the task number! e.g. 'delete 1':)");
            }
            parsedInput[0] = "delete";
            parsedInput[1] = userInput.substring(7);
            return parsedInput;
        } else if (userInput.startsWith("list")) {
            if (userInput.length() > 4) {
                throw new FloofyException("To list your tasks, just type 'list' :)");
            }
            parsedInput[0] = "list";
            return parsedInput;
        } else if (userInput.startsWith("bye")) {
            if (userInput.length() > 3) {
                throw new FloofyException("To exit, just type 'bye' :)");
            }
            parsedInput[0] = "bye";
            return parsedInput;
        } else {
            parsedInput[0] = "invalid";
            return parsedInput;
        }
    }
}
