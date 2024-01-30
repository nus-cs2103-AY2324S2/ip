package ezra;

import java.time.format.DateTimeParseException;

import java.util.regex.Pattern;

public class Parser {
    public static void read(String input, Storage storage, TaskList tasks) {
        Ui.horizontalLine();
        try {
            if (input.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                tasks.listTasks();
            } else if (input.startsWith("mark")) {
                tasks.mark(Parser.parseMark(input), storage);
            } else if (input.startsWith("unmark")) {
                tasks.unmark(Parser.parseUnmark(input), storage);
            } else if (input.startsWith("delete")) {
                tasks.delete(Parser.parseDelete(input), storage);
            } else if (input.startsWith("todo")) {
                tasks.updateTasks(Parser.parseToDo(input), storage);
            } else if (input.startsWith("deadline")) {
                tasks.updateTasks(Parser.parseDeadline(input), storage);
            } else if (input.startsWith("event")) {
                tasks.updateTasks(Parser.parseEvent(input), storage);
            } else {
                System.out.println("\tInvalid command");
            }
        } catch (WrongFormatException e) {
            System.out.println("\t" + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("\tDate time must be in this format: 28/01/2023 1800");
        }
        Ui.horizontalLine();
    }

    public static ToDo parseToDo(String input) throws WrongFormatException {
        if (Pattern.matches("todo\\s\\S.*", input)) {
            String description = input.split("\\s", 2)[1];
            return new ToDo(description);
        } else {
            throw new WrongFormatException("Invalid 'todo' command format. Usage: todo <description>");
        }
    }

    public static Deadline parseDeadline(String input) throws WrongFormatException {
        if (Pattern.matches("deadline\\s\\S.*\\s/by\\s\\S.*", input)) {
            String[] arr = input.split("\\s/by\\s");
            String by = arr[1];
            String description = arr[0].split("\\s", 2)[1];
            return new Deadline(description, by);
        } else {
            throw new WrongFormatException(
                    "Invalid 'deadline' command format. Usage: deadline <description> /by <date time>");
        }
    }

    public static Event parseEvent(String input) throws WrongFormatException {
        if (Pattern.matches("event\\s\\S.*\\s/from\\s\\S.*\\s/to\\s\\S.*", input)) {
            String[] splitTo = input.split("\\s/to\\s");
            String to = splitTo[1];
            String[] splitFrom = splitTo[0].split("\\s/from\\s");
            String from = splitFrom[1];
            String description = splitFrom[0].split("\\s", 2)[1];
            return new Event(description, from, to);
        } else {
            throw new WrongFormatException(
                    "Invalid 'event' command format. Usage: event <description> /from <date time> /to <date time>");
        }
    }

    public static int parseDelete(String input) throws WrongFormatException {
        if (Pattern.matches("delete\\s\\d+", input)) {
            return Integer.parseInt(input.split("\\s")[1]) - 1;
        } else {
            throw new WrongFormatException("Invalid 'delete' command format. Usage: delete <existing task number>");
        }
    }

    public static int parseMark(String input) throws WrongFormatException {
        if (Pattern.matches("mark\\s\\d+", input)) {
            return Integer.parseInt(input.split("\\s")[1]) - 1;
        } else {
            throw new WrongFormatException("Invalid 'mark' command format. Usage: mark <existing task number>");
        }
    }

    public static int parseUnmark(String input) throws WrongFormatException {
        if (Pattern.matches("unmark\\s\\d+", input)) {
            return Integer.parseInt(input.split("\\s")[1]) - 1;
        } else {
            throw new WrongFormatException("Invalid 'unmark' command format. Usage: unmark <existing task number>");
        }
    }
}
