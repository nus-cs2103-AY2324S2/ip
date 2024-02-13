package chatbot.cortana;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chatbot.exception.InvalidInputException;
import chatbot.task.DeadlineTask;
import chatbot.task.EventTask;
import chatbot.task.TaskList;
import chatbot.task.TodoTask;

/**
 * Parser class to parse user input and validate input
 */
public class Parser {

    /**
     * Check if the string is numeric
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * Formats the date time string to be in the correct format for LocalDateTime.parse
     * @param timeString the date time string to be formatted
     * @return the formatted date time string
     */
    private static String formatDateTimeString(String timeString) {
        timeString = timeString.trim();
        timeString = timeString.replace("/", "-");
        String[] arr = timeString.split(" ");
        String time = arr[1].trim();
        String hour = time.substring(0, 2);
        String minute = time.substring(2, 4);
        return arr[0] + "T" + hour + ":" + minute + ":" + "00";
    }

    /**
     * Parses the date time string to a LocalDateTime object
     * @param timeString the date time string to be parsed
     * @return the LocalDateTime object
     */
    public static LocalDateTime parseDateTimeString(String timeString) {
        return LocalDateTime.parse(formatDateTimeString(timeString));
    }

    /**
     * Validates the user input
     * @param command the command to be executed
     * @param input the user input
     * @param taskList the list of tasks
     * @throws InvalidInputException if the input is invalid
     */
    public static void validateInput(Command command, String input, TaskList taskList) throws InvalidInputException {
        if (command == Command.TODO) {
            if (input.length() <= 4) {
                throw new InvalidInputException("Please enter a valid todo! "
                                                + "Tip: todo <description> \nMissing description");
            }
        } else if (command == Command.DEADLINE) {
            if (input.length() > 8) {
                String[] arr = input.substring(9).split("/by");
                if (arr.length != 2) {
                    throw new InvalidInputException("Please enter a valid deadline! "
                                                    + "Tip: deadline <description> /by <deadline> \nMissing /by");
                } else {
                    try {
                        LocalDateTime.parse(formatDateTimeString(arr[1]));
                    } catch (Exception e) {
                        throw new InvalidInputException("Please enter a valid deadline! "
                                                        + "Tip: deadline <description> /by <deadline>"
                                                        + "\nInvalid deadline");
                    }
                }
            } else {
                throw new InvalidInputException("Please enter a valid deadline! "
                                                + "Tip: deadline <description> /by <deadline> \nMissing description");
            }
        } else if (command == Command.EVENT) {
            if (input.length() > 5) {
                String[] arr = input.substring(6).split("/from");
                if (arr.length == 2) {
                    String[] arr2 = arr[1].split("/to");
                    if (arr2.length != 2) {
                        throw new InvalidInputException("Please enter a valid event! "
                                                        + "Tip: event <description> /from <start> /to <end>"
                                                        + "\nMissing /to");
                    } else {
                        try {
                            LocalDateTime.parse(formatDateTimeString(arr2[0].trim()));
                            LocalDateTime.parse(formatDateTimeString(arr2[1].trim()));
                        } catch (Exception e) {
                            throw new InvalidInputException("Please enter a valid event! "
                                                            + "Tip: event <description> /from <start> /to <end>"
                                                            + "\nInvalid start or end time");
                        }
                    }
                } else {
                    throw new InvalidInputException("Please enter a valid event! "
                                                    + "Tip: event <description> /from <start> /to <end>"
                                                    + "\nMissing /from");
                }
            } else {
                throw new InvalidInputException("Please enter a valid event! "
                                                + "Tip: event <description> /from <start> /to <end>"
                                                + "\nMissing description");
            }
        } else if (command == Command.MARK) {
            if (input.length() > 4) {
                String suffix = input.substring(5);
                if (isNumeric(suffix)) {
                    int index = Integer.parseInt(suffix) - 1;
                    if (taskList.getNumTasks() <= index) {
                        throw new InvalidInputException("Please enter a valid number!"
                                                        + "Tip: mark <number> \nNumber out of range");
                    }
                } else {
                    throw new InvalidInputException("Please enter a valid number!"
                                                    + "Tip: mark <number> \nMissing number");
                }
            } else {
                throw new InvalidInputException("Please enter a valid number!"
                                                + "Tip: mark <number> \nMissing number");
            }
        } else if (command == Command.UNMARK) {
            if (input.length() > 6) {
                String suffix = input.substring(7);
                if (isNumeric(suffix)) {
                    int index = Integer.parseInt(suffix) - 1;
                    if (taskList.getNumTasks() <= index) {
                        throw new InvalidInputException("Please enter a valid number! "
                                                        + "Tip: unmark <number> \nNumber out of range");
                    }
                } else {
                    throw new InvalidInputException("Please enter a valid number! "
                                                    + "Tip: unmark <number> \nMissing number");
                }
            } else {
                throw new InvalidInputException("Please enter a valid number! "
                                                + "Tip: unmark <number> \nMissing number");
            }
        } else if (command == Command.DELETE) {
            if (input.length() > 6) {
                String suffix = input.substring(7);
                if (isNumeric(suffix)) {
                    int index = Integer.parseInt(suffix) - 1;
                    if (taskList.getNumTasks() <= index) {
                        throw new InvalidInputException("Please enter a valid number!"
                                                        + "Tip: delete <number> \nNumber out of range");
                    }
                } else {
                    throw new InvalidInputException("Please enter a valid number!"
                                                    + "Tip: delete <number> \nMissing number");
                }
            } else {
                throw new InvalidInputException("Please enter a valid number!"
                                                + "Tip: delete <number> \nMissing number");
            }
        } else if (command == Command.FIND) {
            if (input.length() <= 4) {
                throw new InvalidInputException("Please enter a valid keyword!"
                                                + "Tip: find <keyword> \nMissing keyword");
            }
        }
    }

    /**
     * Parses the command from the user input
     * @param input the user input
     * @return the command
     */
    public static Command parseCommand(String input) {
        if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("mark")) {
            return Command.MARK;
        } else if (input.startsWith("unmark")) {
            return Command.UNMARK;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.startsWith("find")) {
            return Command.FIND;
        } else {
            return Command.INVALID;
        }
    }

    /**
     * Parses the index from the user input
     * @param command the command to be executed
     * @param input the user input
     * @return the index
     */
    public static int parseIndex(Command command, String input) {
        if (command == Command.MARK) {
            return Integer.parseInt(input.substring(5)) - 1;
        } else if (command == Command.UNMARK) {
            return Integer.parseInt(input.substring(7)) - 1;
        } else if (command == Command.DELETE) {
            return Integer.parseInt(input.substring(7)) - 1;
        } else {
            return -1;
        }
    }

    /**
     * Parses the todo task from the user input
     * @param input the user input
     * @return the todo task
     */
    public static TodoTask parseTodoTask(String input) {
        assert input.startsWith("todo") && input.length() > 4;
        return new TodoTask(input.substring(5));
    }

    /**
     * Parses the deadline task from the user input
     * @param input the user input
     * @return the deadline task
     */
    public static DeadlineTask parseDeadlineTask(String input) {
        assert input.startsWith("deadline") && input.length() > 8;
        String[] arr = input.substring(9).split("/by");
        LocalDateTime dateTime = parseDateTimeString(arr[1].trim());
        return new DeadlineTask(arr[0].trim(), dateTime);
    }

    /**
     * Parses the event task from the user input
     * @param input the user input
     * @return the event task
     */
    public static EventTask parseEventTask(String input) {
        assert input.startsWith("event") && input.length() > 5;
        String[] arr = input.substring(6).split("/from");
        String[] arr2 = arr[1].split("/to");
        LocalDateTime startDateTime = parseDateTimeString(arr2[0].trim());
        LocalDateTime endDateTime = parseDateTimeString(arr2[1].trim());
        return new EventTask(arr[0].trim(), startDateTime, endDateTime);
    }

    /**
     * Parses the find string from the user input
     * @param input the user input
     * @return the find string
     */
    public static String parseFindString(String input) {
        assert input.startsWith("find") && input.length() > 4;
        return input.substring(5).trim();
    }

}
