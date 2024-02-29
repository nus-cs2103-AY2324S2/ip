package teletubbi;

/**
 * Represents the parser responsible for interpreting user commands.
 * This class contains methods to parse raw user input into executable commands,
 * allowing the chatbot to understand and respond to user requests.
 * It facilitates the conversion of user input strings into appropriate command objects for further execution.
 */
public class Parser {
    public Parser() {

    }

    /**
     * Parses the input into their respective Command objects.
     *
     * @param input User input.
     * @return Command to be executed.
     * @throws TeletubbiException If command is invalid or incomplete.
     */
    public Command parse(String input) throws TeletubbiException {
        String cleanedInput = input.replaceAll(" ", "");

        if (cleanedInput.equals("help")) {
            return new HelpCommand();
        }
        if (cleanedInput.equals("bye")) {
            return new ExitCommand();
        }
        if (cleanedInput.equals("list")) {
            return new ListCommand();
        }
        if (cleanedInput.startsWith("find")) {
            return parseFindCommand(input);
        }
        if (cleanedInput.startsWith("mark")) {
            return parseMarkCommand(cleanedInput);
        }
        if (input.startsWith("unmark")) {
            return parseUnmarkCommand(cleanedInput);
        }
        if (input.startsWith("delete")) {
            return parseDeleteCommand(cleanedInput);
        }
        return parseAddCommand(input);
    }

    /**
     * Parses the find command into a FindCommand object.
     *
     * @param input User input.
     * @return FindCommand with the relevant keyword.
     * @throws TeletubbiException If the keyword to find is not specified.
     */
    private Command parseFindCommand(String input) throws TeletubbiException {
        if (input.length() < 6) {
            throw new TeletubbiException("find what");
        }
        String keyword = input.split("find ")[1];
        return new FindCommand(keyword);
    }

    /**
     * Parses the mark command into a MarkCommand object.
     *
     * @param input User input.
     * @return MarkCommand with the index of task to be marked.
     * @throws TeletubbiException If the task number is missing or invalid.
     */
    private Command parseMarkCommand(String input) throws TeletubbiException {
        try {
            String[] parts = input.split("mark");
            if (parts.length < 2) {
                throw new TeletubbiException("Missing task number...");
            }
            int taskNumber = Integer.parseInt(parts[1]);
            return new MarkCommand(taskNumber);
        } catch (IndexOutOfBoundsException b) {
            throw new TeletubbiException("Invalid task number... count properly xx");
        } catch (NumberFormatException n) {
            throw new TeletubbiException("number only...");
        }
    }

    /**
     * Parses the unmark command into an UnmarkCommand object.
     *
     * @param input User input.
     * @return UnmarkCommand with the index of task to be unmarked.
     * @throws TeletubbiException If the task number is missing or invalid.
     */
    private Command parseUnmarkCommand(String input) throws TeletubbiException {
        try {
            String[] parts = input.split("unmark");
            if (parts.length < 2) {
                throw new TeletubbiException("Missing task number...");
            }
            int taskNumber = Integer.parseInt(parts[1]);
            return new UnmarkCommand(taskNumber);
        } catch (IndexOutOfBoundsException b) {
            throw new TeletubbiException("Invalid task number... count properly xx");
        } catch (NumberFormatException n) {
            throw new TeletubbiException("number only...");
        }
    }

    /**
     * Parses the delete command into a DeleteCommand object.
     *
     * @param input User input.
     * @return DeleteCommand with the index of task to be deleted.
     * @throws TeletubbiException If the task number is missing or invalid.
     */
    private Command parseDeleteCommand(String input) throws TeletubbiException {
        try {
            String taskNumber = input.replaceAll("delete", "").replaceAll(" ", "");
            if (taskNumber.length() < 1) {
                throw new TeletubbiException("which task?");
            }
            int task = Integer.parseInt(taskNumber);
            return new DeleteCommand(task);
        } catch (IndexOutOfBoundsException e) {
            throw new TeletubbiException("invalid task number... count properly xx");
        } catch (NumberFormatException n) {
            throw new TeletubbiException("number only...");
        }
    }

    /**
     * Parses the add command into an AddCommand object.
     *
     * @param input User input.
     * @return AddCommand with the index of task to be added.
     * @throws TeletubbiException If the task details are missing or invalid, or if the command is not supported.
     */
    private Command parseAddCommand(String input) throws TeletubbiException {
        try {
            Task taskAdded;
            if (input.startsWith("todo")) {
                if (input.length() < 6) {
                    throw new TeletubbiException("do what?");
                }
                taskAdded = new ToDo(input.substring(5));
            } else if (input.startsWith("deadline")) {
                if (input.length() < 12) {
                    throw new TeletubbiException("what's the task?");
                }
                String description = input.split("deadline ")[1].split(" /by")[0];
                String by = input.split("/by ", 2)[1];
                taskAdded = new Deadline(description, by);
            } else if (input.startsWith("event")) {
                String description = input.split("event ")[1].split(" /from")[0];
                String start = input.split("/from ", 2)[1].split(" /to")[0];
                String end = input.split("/to ")[1];
                taskAdded = new Event(description, start, end);
            } else {
                throw new TeletubbiException("gong mat yeh");
            }
            return new AddCommand(taskAdded);
        } catch (IndexOutOfBoundsException a) {
            throw new TeletubbiException("please follow input format...");
        }
    }
}
