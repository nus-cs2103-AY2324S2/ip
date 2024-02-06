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

        if (input.startsWith("find")) {
            if (input.length() < 6) {
                throw new DukeException("find what");
            }
            String keyword = input.split("find ")[1];
            return new FindCommand(keyword);
        }

        if (input.startsWith("mark")) {
            try {
                String[] parts = input.split("mark ");
                if (parts.length < 2) {
                    throw new DukeException("Missing task number...");
                }
                int taskNumber = Integer.parseInt(parts[1]);
                return new MarkCommand(taskNumber);
            } catch (IndexOutOfBoundsException b) {
                throw new DukeException("Invalid task number... count properly xx");
            } catch (NumberFormatException n) {
                throw new DukeException("number only...");
            }
        } else if (input.startsWith("unmark")) {
            try {
                String[] parts = input.split("unmark ");
                if (parts.length < 2) {
                    throw new DukeException("Missing task number...");
                }
                int taskNumber = Integer.parseInt(parts[1]);
                return new UnmarkCommand(taskNumber);
            } catch (IndexOutOfBoundsException b) {
                throw new DukeException("Invalid task number... count properly xx");
            } catch (NumberFormatException n) {
                throw new DukeException("number only...");
            }
        }

        if (input.startsWith("delete")) {
            try {
                String taskNumber = input.replaceAll("delete", "").replaceAll(" ", "");
                if (taskNumber.length() < 1) {
                    throw new DukeException("which task?");
                }
                int task = Integer.parseInt(taskNumber);
                return new DeleteCommand(task);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("invalid task number... count properly xx");
            } catch (NumberFormatException n) {
                throw new DukeException("number only...");
            }
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
