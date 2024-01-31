package osiris.interpreters;

import java.time.LocalDate;
import java.time.LocalDateTime;

import osiris.commands.Command;
import osiris.commands.FindTasksCommand;
import osiris.commands.NoCommand;
import osiris.commands.UnsupportedCommand;
import osiris.commands.addDeadlineTaskCommand;
import osiris.commands.addEventTaskCommand;
import osiris.commands.addToDoTaskCommand;
import osiris.commands.markTaskCompletedCommand;
import osiris.commands.markTaskIncompleteCommand;
import osiris.commands.printUserTasksCommand;
import osiris.commands.removeTaskCommand;
import osiris.commands.terminateChatCommand;
import osiris.formatters.DateTimeFormatters;
import osiris.validation.InputsValidator;

/**
 * Interprets user input and returns the appropriate command.
 */
public class UserInputInterpreter {

    private static UserInputInterpreter instance;

    private UserInputInterpreter() {}

    /**
     * Returns the singleton instance of the UserInputInterpreter.
     *
     * @return The UserInputInterpreter instance.
     */
    public static UserInputInterpreter getInstance() {
        if (instance == null) {
            instance = new UserInputInterpreter();
        }
        return instance;
    }

    /**
     * Interprets the user input and returns the corresponding command.
     *
     * @param userInput The user input string.
     * @return The appropriate Command object based on the user input.
     */
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
                LocalDateTime[] dateTimeRange = DateTimeFormatters.getInstance()
                        .userInputDateTimeRangeFormatter(startDateTimeStr, endDateTimeStr);
                if (dateTimeRange != null) {
                    return new addEventTaskCommand(taskName, dateTimeRange[0], dateTimeRange[1]);
                }
            }
            break;

        case FindTasksCommand.COMMAND:
            String searhString = userInput.substring(FindTasksCommand.COMMAND.length()).trim();
            return new FindTasksCommand(searhString);

        default:
            return new UnsupportedCommand();
        }
        return new NoCommand();
    }
}
