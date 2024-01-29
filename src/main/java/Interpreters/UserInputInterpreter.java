package Interpreters;

import Commands.*;
import Validation.InputsValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UserInputInterpreter {

    private static UserInputInterpreter instance;

    private UserInputInterpreter() {}

    public static UserInputInterpreter getInstance() {
        if (instance == null) {
            instance = new UserInputInterpreter();
        }
        return instance;
    }

    public Command interpretUserInput(String userInput) {
        String[] inputtedWords = userInput.split(" ");
        String taskName;
        boolean isValid;

        switch (inputtedWords[0]) {

            case terminateChatCommand.COMMAND:
                return new terminateChatCommand();

            case printUserTasksCommand.COMMAND:
                return new printUserTasksCommand();

            case markTaskCompletedCommand.COMMAND:
                isValid = InputsValidator.getInstance().validateMarkTaskCompletedInput(userInput);
                if (isValid) {
                    String taskIndexString = inputtedWords[1];
                    int taskIndex = Integer.parseInt(taskIndexString);
                    return new markTaskCompletedCommand(taskIndex);
                }
                break;

            case markTaskIncompleteCommand.COMMAND:
                isValid = InputsValidator.getInstance().validateMarkTaskIncompleteInput(userInput);
                if (isValid) {
                    String taskIndexString = inputtedWords[1];
                    int taskIndex = Integer.parseInt(taskIndexString);
                    return new markTaskIncompleteCommand(taskIndex);
                }
                break;

            case removeTaskCommand.COMMAND:
                isValid = InputsValidator.getInstance().validateRemoveTaskInput(userInput);
                if (isValid) {
                    String taskIndexString = inputtedWords[1];
                    int taskIndex = Integer.parseInt(taskIndexString);
                    return new removeTaskCommand(taskIndex);
                }
                break;

            case addToDoTaskCommand.COMMAND:
                isValid = InputsValidator.getInstance().validateAddToDoTaskInput(userInput);
                if (isValid) {
                    taskName = userInput.substring(addToDoTaskCommand.COMMAND.length()).trim();
                    return new addToDoTaskCommand(taskName);
                }
                break;

            case addDeadlineTaskCommand.COMMAND:
                isValid = InputsValidator.getInstance().validateAddDeadlineTaskInput(userInput);
                if (isValid) {
                    int byIndex = userInput.indexOf("/by");
                    taskName = userInput.substring(addDeadlineTaskCommand.COMMAND.length(), byIndex - 1).trim();
                    String deadline = userInput.substring(byIndex + "/by".length()).trim();
                    System.out.println(deadline);
                    return new addDeadlineTaskCommand(taskName, deadline);
                }
                break;

            case addEventTaskCommand.COMMAND:
                isValid = InputsValidator.getInstance().validateAddEventTaskInput(userInput);
                if (isValid) {
                    int fromIndex = userInput.indexOf("/from");
                    int toIndex = userInput.indexOf("/to");
                    taskName = userInput.substring(addEventTaskCommand.COMMAND.length(), fromIndex - 1).trim();
                    String startDateTime = userInput.substring(fromIndex + "/from".length(), toIndex - 1).trim();
                    String endDateTime = userInput.substring(toIndex + "/to".length()).trim();
                    return new addEventTaskCommand(taskName, startDateTime, endDateTime);
                }
                break;

            default:
                return new UnsupportedCommand();
        }
        return null;
    }

    public static LocalDate dateFormatter(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            LocalDate date = LocalDate.parse(dateStr, dateTimeFormatter);
            return date;
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date-time string: '" + dateStr);
            System.out.println("Please try /by dd-mm-yyyy for a deadline tasks.");
            return null;
        }
    }

    public static LocalDateTime dateTimeFormatter(String dateTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the time range.");
            System.out.println("Please provide date time range 'dd-MM-yyyy HHmm' format.");
            return null;
        }
    }

    public LocalDateTime[] timeRangeFormatter(String fromDateTimeStr, String toTimeStr) {
        DateTimeFormatter startDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        DateTimeFormatter endDateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");

        try {
            LocalDateTime startDateTime = LocalDateTime.parse(fromDateTimeStr, startDateTimeFormatter);
            LocalDateTime endDateTime = LocalDateTime.parse(toTimeStr, endDateTimeFormatter);
            return new LocalDateTime[]{startDateTime, endDateTime};
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse the date time range.");
            System.out.println("Please provide date time range in 'dd-MM-yyyy HHmm' format.");
            return null;
        }
    }
}
