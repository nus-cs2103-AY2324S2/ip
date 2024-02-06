package duke;

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
     * @throws DukeException If command is invalid or incomplete.
     */
    public Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        }

        if (input.replaceAll(" ", "").equals("list")) {
            return new ListCommand();
        }

        if (input.startsWith("mark")) {
            String[] parts = input.split("mark ");
            if (parts.length < 2) {
                throw new DukeException("Missing task number...");
            }
            int taskNumber = Integer.parseInt(parts[1]);
            return new MarkCommand(taskNumber);
        } else if (input.startsWith("unmark")) {
            String[] parts = input.split("unmark ");
            if (parts.length < 2) {
                throw new DukeException("Missing task number...");
            }
            int taskNumber = Integer.parseInt(parts[1]);
            return new UnmarkCommand(taskNumber);
        }

        if (input.startsWith("delete")) {
            String taskNumber = input.replaceAll("delete", "").replaceAll(" ","");
            if (taskNumber.length() < 1) {
                throw new DukeException("which task?");
            }
            int task = Integer.parseInt(taskNumber);
            return new DeleteCommand(task);
        } else {
            try {
                Task taskAdded;
                if (input.startsWith("todo")) {
                    if (input.length() < 6) {
                        throw new DukeException("do what?");
                    }
                    ToDo td = new ToDo(input.substring(5));
                    taskAdded = td;
                } else if (input.startsWith("deadline")) {
                    if (input.length() < 12) {
                        throw new DukeException("what's the task?");
                    }
                    String by = input.split("/by ", 2)[1];
                    String description = input.split(" ", 2)[1].split(" /by")[0];
                    Deadline d = new Deadline(description, by);
                    taskAdded = d;
                } else if (input.startsWith("event")) {
                    String description = input.split(" ", 2)[1].split(" /from")[0];
                    String start = input.split("/from ", 2)[1].split(" /to")[0];
                    String end = input.split("/to ")[1];
                    Event e = new Event(description, start, end);
                    taskAdded = e;
                } else {
                    throw new DukeException("gong mat yeh");
                }
                return new AddCommand(taskAdded);
            } catch (IndexOutOfBoundsException a) {
                throw new DukeException("please follow input format...");
            }
        }
    }
}
