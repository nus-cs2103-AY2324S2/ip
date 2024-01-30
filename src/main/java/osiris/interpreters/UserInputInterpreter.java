package osiris.interpreters;

import osiris.commands.*;
import osiris.formatters.DateTimeFormatters;
import osiris.validation.InputsValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
                    String deadlineStr = userInput.substring(byIndex + "/by".length()).trim();
                    LocalDate deadline = DateTimeFormatters.getInstance().userInputDateFormatter(deadlineStr);
                    if (deadline != null) {
                        return new addDeadlineTaskCommand(taskName, deadline);
                    }
                }
                break;

            case addEventTaskCommand.COMMAND:
                isValid = InputsValidator.getInstance().validateAddEventTaskInput(userInput);
                if (isValid) {
                    int fromIndex = userInput.indexOf("/from");
                    int toIndex = userInput.indexOf("/to");
                    taskName = userInput.substring(addEventTaskCommand.COMMAND.length(), fromIndex - 1).trim();
                    String startDateTimeStr = userInput.substring(fromIndex + "/from".length(), toIndex - 1).trim();
                    String endDateTimeStr = userInput.substring(toIndex + "/to".length()).trim();
                    LocalDateTime[] dateTimeRange = DateTimeFormatters.getInstance().userInputDateTimeRangeFormatter(startDateTimeStr, endDateTimeStr);
                    if (dateTimeRange != null) {
                        return new addEventTaskCommand(taskName, dateTimeRange[0], dateTimeRange[1]);
                    }
                }
                break;

            default:
                return new UnsupportedCommand();
        }
        return new NoCommand();
    }

}
