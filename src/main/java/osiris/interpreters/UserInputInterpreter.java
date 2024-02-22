package osiris.interpreters;

import java.time.LocalDate;
import java.time.LocalDateTime;

import osiris.commands.AddDeadlineTaskCommand;
import osiris.commands.AddEventTaskCommand;
import osiris.commands.AddToDoTaskCommand;
import osiris.commands.Command;
import osiris.commands.DeleteTaskCommand;
import osiris.commands.FindTasksCommand;
import osiris.commands.MarkTaskCompleteCommand;
import osiris.commands.MarkTaskIncompleteCommand;
import osiris.commands.NoCommand;
import osiris.commands.PrintUserTasksCommand;
import osiris.commands.TerminateChatCommand;
import osiris.commands.UnsupportedCommand;
import osiris.exceptions.OsirisException;
import osiris.formatters.DateTimeFormatters;
import osiris.validation.InputsValidator;

/**
 * Interprets user input and returns the appropriate command.
 */
public class UserInputInterpreter {

    /** A singleton UserInputInterpreter instance. */
    private static UserInputInterpreter instance;

    /** Private constructor to initialise an UserInputInterpreter instance. */
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
     * @throws OsirisException If there is an error in interpreting data.
     */
    public Command interpretUserInput(String userInput) throws OsirisException {

        assert userInput != null : "User input must not be null";

        String[] inputtedWords = userInput.split(" ");
        String taskName = "";
        boolean isValid = false;

        assert inputtedWords.length > 0 : "Inputted words array must not be empty";

        switch (inputtedWords[0]) {
        case TerminateChatCommand.COMMAND:
            return new TerminateChatCommand();

        case PrintUserTasksCommand.COMMAND:
            return new PrintUserTasksCommand();

        case MarkTaskCompleteCommand.COMMAND:
            isValid = InputsValidator.getInstance().isMarkTaskCompleteInputValid(userInput);
            if (isValid) {
                String taskIndexString = inputtedWords[1];
                int taskIndex = Integer.parseInt(taskIndexString);
                return new MarkTaskCompleteCommand(taskIndex);
            }
            break;

        case MarkTaskIncompleteCommand.COMMAND:
            isValid = InputsValidator.getInstance().isMarkTaskIncompleteInputValid(userInput);
            if (isValid) {
                String taskIndexString = inputtedWords[1];
                int taskIndex = Integer.parseInt(taskIndexString);
                return new MarkTaskIncompleteCommand(taskIndex);
            }
            break;

        case DeleteTaskCommand.COMMAND:
            isValid = InputsValidator.getInstance().isDeleteTaskInputValid(userInput);
            if (isValid) {
                String taskIndexString = inputtedWords[1];
                int taskIndex = Integer.parseInt(taskIndexString);
                return new DeleteTaskCommand(taskIndex);
            }
            break;

        case AddToDoTaskCommand.COMMAND:
            isValid = InputsValidator.getInstance().isAddToDoTaskInputValid(userInput);
            if (isValid) {
                taskName = userInput.substring(AddToDoTaskCommand.COMMAND.length()).trim();
                return new AddToDoTaskCommand(taskName);
            }
            break;

        case AddDeadlineTaskCommand.COMMAND:
            isValid = InputsValidator.getInstance().isAddDeadlineTaskInputValid(userInput);
            if (isValid) {
                int byIndex = userInput.indexOf("/by");
                taskName = userInput.substring(AddDeadlineTaskCommand.COMMAND.length(), byIndex - 1).trim();
                String deadlineStr = userInput.substring(byIndex + "/by".length()).trim();
                LocalDate deadline = DateTimeFormatters.getInstance().formatUserInputDate(deadlineStr);
                return new AddDeadlineTaskCommand(taskName, deadline);
            }
            break;

        case AddEventTaskCommand.COMMAND:
            isValid = InputsValidator.getInstance().isAddEventTaskInputValid(userInput);
            if (isValid) {
                int fromIndex = userInput.indexOf("/from");
                int toIndex = userInput.indexOf("/to");
                taskName = userInput.substring(AddEventTaskCommand.COMMAND.length(), fromIndex - 1).trim();
                String startDateTimeStr = userInput.substring(fromIndex + "/from".length(), toIndex - 1).trim();
                String endDateTimeStr = userInput.substring(toIndex + "/to".length()).trim();
                LocalDateTime[] dateTimeRange = DateTimeFormatters.getInstance()
                        .formatUserInputDateTimeRange(startDateTimeStr, endDateTimeStr);
                return new AddEventTaskCommand(taskName, dateTimeRange[0], dateTimeRange[1]);
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
