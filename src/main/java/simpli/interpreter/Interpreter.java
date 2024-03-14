package simpli.interpreter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import simpli.commands.base.CommandManager;
import simpli.commands.base.CommandResult;
import simpli.exceptions.CommandException;
import simpli.exceptions.TaskException;
import simpli.tasks.TaskList;

/**
 * Interpret and executes the tokens.
 */
public class Interpreter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private final CommandManager commandManager;

    /**
     * Initializes the interpreter with the specified ui object and taskList.
     *
     * @param taskList manages tasks and their actions.
     */
    public Interpreter(TaskList taskList) {
        assert taskList != null : "TaskList object not found";
        this.commandManager = new CommandManager(this, taskList);
    }

    /**
     * Interprets and executes the specified actions.
     *
     * @param tokens array of token.
     * @return the string to be shown to the user.
     * @throws CommandException when no such action exists.
     * @throws TaskException when task have invalid parameters.
     */
    public CommandResult interpret(String[] tokens) throws CommandException, TaskException {
        return commandManager.execute(tokens);
    }

    /**
     * Interpret the tokens as LocalDateTime.
     *
     * @param tokens array of token.
     * @return LocalDateTime object.
     * @throws TaskException when the date and time format is invalid.
     */
    public LocalDateTime[] interpretLocalDateTime(String[] tokens) throws TaskException {
        LocalDateTime[] dates = new LocalDateTime[tokens.length];
        Arrays.fill(dates, LocalDateTime.MIN);
        try {
            fillLocalDateTime(tokens, dates, 3);
        } catch (DateTimeParseException e) {
            throw new TaskException();
        }
        return dates;
    }

    /**
     * Fills an array with dates using an array of tokens.
     *
     * @param tokens array of token.
     * @param dates array of LocalDateTime objects.
     * @param dateStartIndex starting index of the token to be interpreted as LocalDateTime.
     */
    private void fillLocalDateTime(String[] tokens, LocalDateTime[] dates, int dateStartIndex) {
        for (int i = dateStartIndex; i < tokens.length; i++) {
            if (tokens[i].isEmpty()) {
                break;
            }
            dates[i - dateStartIndex] = LocalDateTime.parse(tokens[i], FORMATTER);
        }
    }

    /**
     * Checks whether the dates are not in the past and the
     * to date must come after the from date.
     *
     * @param dates an array of LocalDateTime objects
     * @return true if date satisfy the criteria, false if otherwise.
     */
    public boolean isValidDateTime(LocalDateTime[] dates) {
        return Arrays.stream(dates)
                .allMatch((localDateTime) -> localDateTime.isAfter(LocalDateTime.now())
                        || localDateTime.isEqual(LocalDateTime.MIN));
    }
}
