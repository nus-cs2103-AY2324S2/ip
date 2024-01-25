import java.util.InputMismatchException;

public class CommandParser {
    private final String source;
    private final String[] commands;
    private final int numberOfCommands;

    CommandParser(String source) {
        this.source = source;
        this.commands = source.split(" ", 2);
        this.numberOfCommands = commands.length;
    }

    public Command parse() throws MikeException {
        String primaryCommand = commands[0];
        switch (primaryCommand) {
            case "bye": return parseBy();
            case "list": return parseList();
            case "mark": return parseMark();
            case "unmark": return parseUnmark();
            case "todo": return parseTodo();
            case "deadline": return parseDeadline();
            case "event": return parseEvent();
            default: {
                throw new MikeException("That is the weirdest thing you've ever said.");
            }
        }
    }

    private Command parseBy() throws MikeException {
        if (numberOfCommands != 1) {
            throw new MikeException("Usage: bye");
        }
        return new ExitCommand();
    }

    private Command parseList() throws MikeException {
        if (numberOfCommands != 1) {
            throw new MikeException("Usage: list");
        }
        return new ListCommand();
    }

    private Command parseMark() throws MikeException {
        if (numberOfCommands != 2) {
            throw new MikeException("Usage: mark [number]");
        }

        String argument = commands[1];

        try {
            int taskNumber = Integer.parseInt(argument);
            return new MarkCommand(taskNumber);
        } catch(InputMismatchException e) {
            String errorMessage =
                    "One, two, three, four, get the kid back through the door!\n" +
                            String.format("'%s' is not an integer Sulley...", argument);
            throw new MikeException(errorMessage);
        }
    }

    private Command parseUnmark() throws MikeException {
        if (numberOfCommands != 2) {
            throw new MikeException("Usage: unmark [number]");
        }

        String argument = commands[1];
        try {
            int taskNumber = Integer.parseInt(argument);
            return new UnmarkCommand(taskNumber);
        } catch (InputMismatchException e) {
            String errorMessage =
                    "One, two, three, four, get the kid back through the door!\n" +
                            String.format("'%s' is not an integer Sulley...", argument);
            throw new MikeException(errorMessage);
        }
    }

    private Command parseTodo() throws MikeException {
        if (numberOfCommands != 2) {
            throw new MikeException("The description is missing.\nUsage: todo [description]");
        }

        String taskDescription = commands[1].strip();

        if (taskDescription.isBlank()) {
            throw new MikeException("The description is missing.\nUsage: todo [description]");
        }

        return new AddTodoCommand(taskDescription);
    }

    private Command parseDeadline() throws MikeException {
        if (numberOfCommands != 2) {
            throw new MikeException("Usage: deadline [description] /by [date]");
        }

        String arguments = commands[1];
        String[] descriptionAndDate = arguments.split("/by", 3);
        int numberOfArguments = descriptionAndDate.length;

        if (numberOfArguments != 2) {
            throw new MikeException("Usage: deadline [description] /by [date]");
        }

        String description = descriptionAndDate[0].strip();
        String byDate = descriptionAndDate[1].strip();

        if (description.isBlank()) {
            throw new MikeException("The description is missing Sull.\nUsage: deadline [description] /by [date]");
        } else if (byDate.isBlank()) {
            throw new MikeException("The date is missing Sull.\nUsage: deadline [description] /by [date]");
        }

        return new AddDeadlineCommand(description, byDate);
    }

    private Command parseEvent() throws MikeException {
        if (numberOfCommands != 2) {
            throw new MikeException("Usage: event [description] /from [date] /to [date]");
        }

        String arguments = commands[1];
        String[] descriptionAndDates = arguments.split("/from", 3);

        if (descriptionAndDates.length != 2) {
            throw new MikeException("Usage: event [description] /from [date] /to [date]");
        }

        String description = descriptionAndDates[0].strip();
        String dates = descriptionAndDates[1];

        if (description.isBlank()) {
            throw new MikeException("The description is missing Sull.\nUsage: event [description] /from [date] /to [date]");
        }

        String[] fromAndToDates = dates.split("/to", 3);

        if (fromAndToDates.length != 2) {
            throw new MikeException("Usage: event [description] /from [date] /to [date]");
        }

        String fromDate = fromAndToDates[0].strip();
        String toDate = fromAndToDates[1].strip();

        if (fromDate.isBlank()) {
            throw new MikeException("Required argument missing in '/from [date]'.\nUsage: event [description] /from [date] /to [date]");
        } else if (toDate.isBlank()) {
            throw new MikeException("Required argument missing in '/to [date]'.\nUsage: event [description] /from [date] /to [date]");
        }

        return new AddEventCommand(description, fromDate, toDate);
    }
}
