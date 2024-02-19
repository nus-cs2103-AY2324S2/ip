package chatbot.cortana;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chatbot.exception.InvalidInputException;
import chatbot.task.TaskList;

/**
 * Validator class to validate user input
 */
public class Validator {

    /**
     * Validates the user input
     * @param command the command to be executed
     * @param input the user input
     * @param taskList the list of tasks
     * @throws InvalidInputException if the input is invalid
     */
    public static void validateInput(Command command, String input, TaskList taskList) throws InvalidInputException {
        switch (command) {
        case TODO:
            validateTodoInput(input);
            break;
        case DEADLINE:
            validateDeadlineInput(input);
            break;
        case EVENT:
            validateEventInput(input);
            break;
        case MARK:
            validateMarkInput(input, taskList);
            break;
        case UNMARK:
            validateUnmarkInput(input, taskList);
            break;
        case DELETE:
            validateDeleteInput(input, taskList);
            break;
        case FIND:
            validateFindInput(input);
            break;
        default:
            break;
        }
    }

    /**
     * Check if the string is numeric
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private static void validateTodoInput(String input) throws InvalidInputException {
        if (input.length() <= 4) {
            throw new InvalidInputException("Please enter a valid todo! "
                                            + "Tip: todo <description> \nMissing description");
        }
    }

    private static void validateDeadlineInput(String input) throws InvalidInputException {
        if (input.length() > 8) {
            String[] arr = input.substring(9).split("/by");
            if (arr.length != 2) {
                throw new InvalidInputException("Please enter a valid deadline! "
                                                + "Tip: deadline <description> /by <deadline> \nMissing /by");
            } else {
                try {
                    LocalDateTime.parse(Utils.formatDateTimeString(arr[1]));
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
    }

    private static void validateEventInput(String input) throws InvalidInputException {
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
                        LocalDateTime.parse(Utils.formatDateTimeString(arr2[0].trim()));
                        LocalDateTime.parse(Utils.formatDateTimeString(arr2[1].trim()));
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
    }

    private static void validateMarkInput(String input, TaskList taskList) throws InvalidInputException {
        if (input.length() > 4) {
            String suffix = input.substring(5);
            if (isNumeric(suffix)) {
                int index = Integer.parseInt(suffix) - 1;
                if (index < 0) {
                    throw new InvalidInputException("Please enter a valid number!"
                                                    + "Tip: mark <number> \nNumber out of range");
                }
                if (index >= taskList.getNumTasks()) {
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
    }

    private static void validateUnmarkInput(String input, TaskList taskList) throws InvalidInputException {
        if (input.length() > 6) {
            String suffix = input.substring(7);
            if (isNumeric(suffix)) {
                int index = Integer.parseInt(suffix) - 1;
                if (index < 0) {
                    throw new InvalidInputException("Please enter a valid number!"
                                                    + "Tip: unmark <number> \nNumber out of range");
                }
                if (index >= taskList.getNumTasks()) {
                    throw new InvalidInputException("Please enter a valid number!"
                                                    + "Tip: unmark <number> \nNumber out of range");
                }
            } else {
                throw new InvalidInputException("Please enter a valid number!"
                                                + "Tip: unmark <number> \nMissing number");
            }
        } else {
            throw new InvalidInputException("Please enter a valid number!"
                                            + "Tip: unmark <number> \nMissing number");
        }
    }

    private static void validateDeleteInput(String input, TaskList taskList) throws InvalidInputException {
        if (input.length() > 6) {
            String suffix = input.substring(7);
            if (isNumeric(suffix)) {
                int index = Integer.parseInt(suffix) - 1;
                if (index < 0) {
                    throw new InvalidInputException("Please enter a valid number!"
                                                    + "Tip: delete <number> \nNumber out of range");
                }
                if (index >= taskList.getNumTasks()) {
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
    }

    private static void validateFindInput(String input) throws InvalidInputException {
        if (input.length() <= 4) {
            throw new InvalidInputException("Please enter a valid keyword!"
                                            + "Tip: find <keyword> \nMissing keyword");
        }
    }

}
