package osiris.validation;

import osiris.commands.AddDeadlineTaskCommand;
import osiris.commands.AddEventTaskCommand;
import osiris.commands.AddToDoTaskCommand;
import osiris.exceptions.OsirisInvalidInputException;
import osiris.formatters.DateTimeFormatters;

/**
 * The InputsValidator class provides methods to validate user inputs for various commands.
 */
public class InputsValidator {

    /** Singleton instance variable */
    private static InputsValidator instance;

    /** Return a new Input Validator instance **/
    private InputsValidator() {}

    /**
     * Retrieves the singleton instance of InputsValidator.
     *
     * @return The singleton instance of InputsValidator.
     */
    public static InputsValidator getInstance() {
        if (instance == null) {
            instance = new InputsValidator();
        }
        return instance;
    }

    /**
     * Validates user input for marking a task as completed.
     *
     * @param userInput The user input string.
     * @return True if the input is valid; otherwise, false.
     * @throws OsirisInvalidInputException If invalid mark command provided.
     */
    public boolean isMarkTaskCompleteInputValid(String userInput) {
        assert userInput != null : "User input must not be null";
        String[] inputtedWords = userInput.split(" ");
        assert inputtedWords.length >= 2 : "Invalid input format";

        if (inputtedWords.length == 2) {
            String taskIndexString = inputtedWords[1];
            if (taskIndexString.matches("\\d+")) {
                return true;
            } else {
                System.err.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
                throw new OsirisInvalidInputException("Invalid task index: "
                        + taskIndexString + ". Please enter a valid integer.");
            }
        } else {
            System.err.println("Invalid task index. Please Reenter");
            throw new OsirisInvalidInputException("Invalid task index. Please Reenter.");
        }
    }

    /**
     * Validates user input for marking a task as incomplete.
     *
     * @param userInput The user input string.
     * @return True if the input is valid; otherwise, false.
     * @throws OsirisInvalidInputException If invalid unmark command provided.
     */
    public boolean isMarkTaskIncompleteInputValid(String userInput) {
        assert userInput != null : "User input must not be null";
        String[] inputtedWords = userInput.split(" ");
        assert inputtedWords.length >= 2 : "Invalid input format";

        if (inputtedWords.length == 2) {
            String taskIndexString = inputtedWords[1];
            if (taskIndexString.matches("\\d+")) {
                return true;
            } else {
                System.err.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
                throw new OsirisInvalidInputException("Invalid task index: "
                        + taskIndexString + ". Please enter a valid integer.");
            }
        } else {
            System.err.println("Invalid task index. Please Reenter.");
            throw new OsirisInvalidInputException("Invalid task index. Please Reenter.");
        }
    }

    /**
     * Validates user input for removing a task.
     *
     * @param userInput The user input string.
     * @return True if the input is valid; otherwise, false.
     * @throws OsirisInvalidInputException If invalid delete command provided.
     */
    public boolean isDeleteTaskInputValid(String userInput) {
        assert userInput != null : "User input must not be null";
        String[] inputtedWords = userInput.split(" ");
        assert inputtedWords.length >= 2 : "Invalid input format";

        if (inputtedWords.length == 2) {
            String taskIndexString = inputtedWords[1];
            if (taskIndexString.matches("\\d+")) {
                return true;
            } else {
                System.err.println("Invalid task index: " + taskIndexString + ". Please enter a valid integer.");
                throw new OsirisInvalidInputException("Invalid task index: "
                        + taskIndexString + ". Please enter a valid integer.");
            }
        } else {
            System.err.println("Invalid task index. Please Reenter");
            throw new OsirisInvalidInputException("Invalid task index. Please Reenter.");
        }
    }

    /**
     * Validates user input for adding a to-do task.
     *
     * @param userInput The user input string.
     * @return True if the input is valid; otherwise, false.
     * @throws OsirisInvalidInputException If invalid todo command provided.
     */
    public boolean isAddToDoTaskInputValid(String userInput) {
        assert userInput != null : "User input must not be null";
        String taskName = userInput.substring(AddToDoTaskCommand.COMMAND.length()).trim();
        if (!taskName.isEmpty()) {
            return true;
        } else {
            System.err.println("Task name not provided. Please Reenter.");
            throw new OsirisInvalidInputException("Task name not provided. Please Reenter.");
        }
    }

    /**
     * Validates user input for adding a deadline task.
     *
     * @param userInput The user input string.
     * @return True if the input is valid; otherwise, false.
     * @throws OsirisInvalidInputException If invalid deadline command provided.
     */
    public boolean isAddDeadlineTaskInputValid(String userInput) {
        assert userInput != null : "User input must not be null";

        int byIndex = userInput.indexOf("/by");
        if (byIndex == -1) {
            System.err.println("Invalid input format. Please Reenter. Ensure '/by' is specified for a Deadline Task. "
                    + "E.g. deadline Do Homework /by dd-MM-yyyy .");
            throw new OsirisInvalidInputException("Invalid input format. Please Reenter. "
                    + "Ensure '/by' is specified for a Deadline Task. "
                    + "E.g. deadline Do Homework /by dd-MM-yyyy .");
        }

        String taskName = userInput.substring(AddDeadlineTaskCommand.COMMAND.length(), byIndex - 1).trim();
        if (taskName.isEmpty()) {
            System.err.println("Task name not provided. Please Reenter.");
            throw new OsirisInvalidInputException("Task name not provided. Please Reenter.");
        }

        String deadline = userInput.substring(byIndex + "/by".length()).trim();
        String[] deadlineParts = deadline.split("-");
        if (deadlineParts.length != 3) {
            System.err.println("Invalid deadline format. Please use dd-MM-yyyy.");
            throw new OsirisInvalidInputException("Invalid deadline format. Please use dd-MM-yyyy.");
        }

        String dayStr = deadlineParts[0];
        String monthStr = deadlineParts[1];
        String yearStr = deadlineParts[2];

        if (!isValidDay(dayStr) || !isValidMonth(monthStr) || !isValidYear(yearStr)) {
            System.err.println("Invalid day, month, or year in deadline. Please use dd-MM-yyyy. ");
            throw new OsirisInvalidInputException("Invalid day, month, or year in deadline. "
                    + "Please use dd-MM-yyyy. ");
        }

        return true;
    }

    /**
     * Validates user input for adding an event task.
     *
     * @param userInput The user input string.
     * @return True if the input is valid; otherwise, false.
     * @throws OsirisInvalidInputException If invalid event command provided.
     */
    public boolean isAddEventTaskInputValid(String userInput) {
        assert userInput != null : "User input must not be null";

        int fromIndex = userInput.indexOf("/from");
        int toIndex = userInput.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || fromIndex >= toIndex) {
            System.err.println("Invalid input format. Please Reenter. "
                    + "Ensure '/from' & '/to' is specified for an Event Task. "
                    + "E.g. event School Meeting /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm. Please Reenter.");
            throw new OsirisInvalidInputException("Invalid input format. Please Reenter. "
                    + "Ensure '/from' & '/to' is specified for an Event Task. "
                    + "E.g. event School Meeting /from dd-MM-yyyy HHmm /to dd-MM-yyyy HHmm. Please Reenter.");
        }

        String taskName = userInput.substring(AddEventTaskCommand.COMMAND.length(), fromIndex - 1).trim();
        if (taskName.isEmpty()) {
            System.err.println("Task name not provided. Please Reenter.");
            throw new OsirisInvalidInputException("Task name not provided. Please Reenter.");
        }

        String startDateTime = userInput.substring(fromIndex + "/from".length(), toIndex - 1).trim();
        String endDateTime = userInput.substring(toIndex + "/to".length()).trim();

        if (!isValidDateTime(startDateTime) || !isValidDateTime(endDateTime)) {
            System.err.println("Invalid date-time format. Please use dd-MM-yyyy HHmm.");
            throw new OsirisInvalidInputException("Invalid date-time format. Please use dd-MM-yyyy HHmm.");
        }

        if (DateTimeFormatters.getInstance().formatUserInputDateTime(startDateTime)
                .isAfter(DateTimeFormatters.getInstance().formatUserInputDateTime(endDateTime))) {
            System.err.println("Invalid date-time order. Start date/time should be before end date/time.");
            throw new OsirisInvalidInputException("Invalid date-time order. "
                    + "Start date/time should be before end date/time.");
        }

        return true;
    }

    // Private helper methods for validation ===========================================================================

    /**
     * Validates if the given day string is a valid day of the month.
     *
     * @param dayStr The string representation of the day.
     * @return true if the day is valid, otherwise false.
     */
    private boolean isValidDay(String dayStr) {
        try {
            int day = Integer.parseInt(dayStr);
            return day >= 1 && day <= 31 && dayStr.length() == 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if the given month string is a valid month of the year.
     *
     * @param monthStr The string representation of the month.
     * @return true if the month is valid, otherwise false.
     */
    private boolean isValidMonth(String monthStr) {
        try {
            int month = Integer.parseInt(monthStr);
            return month >= 1 && month <= 12 && monthStr.length() == 2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if the given year string is a valid year.
     *
     * @param yearStr The string representation of the year.
     * @return true if the year is valid, otherwise false.
     */
    private boolean isValidYear(String yearStr) {
        try {
            int year = Integer.parseInt(yearStr);
            return year > 0 && yearStr.length() == 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates if the given date-time string is in a valid format and represents a valid date and time.
     *
     * @param dateTime The string representation of the date and time.
     * @return true if the date-time is valid, otherwise false.
     */
    private boolean isValidDateTime(String dateTime) {
        try {
            String[] dateTimeParts = dateTime.split(" ");

            if (dateTimeParts.length != 2) {
                return false;
            }

            String[] dateParts = dateTimeParts[0].split("-");

            if (dateParts.length != 3) {
                return false;
            }

            int hour = Integer.parseInt(dateTimeParts[1].substring(0, 2));
            int minute = Integer.parseInt(dateTimeParts[1].substring(2));

            return isValidDay(dateParts[0]) && isValidMonth(dateParts[1]) && isValidYear(dateParts[2])
                    && hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59 && dateTimeParts[1].length() == 4;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
