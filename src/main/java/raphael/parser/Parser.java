package raphael.parser;

import raphael.command.*;
import raphael.exception.RaphaelException;
import raphael.task.Deadline;
import raphael.task.Event;
import raphael.task.Task;
import raphael.task.Todo;

/**
 * Parses the command line input
 */
public class Parser {
    /**
     * Returns a command object after interpreting the input received from the command line. This method will throw
     * exception if the input does not fulfill the required format.
     *
     * @param input the input string read from command line
     * @return a command that corresponding to the keyword entered
     * @throws RaphaelException exception exclusive to Raphael
     */
    public static Command parse(String input) throws RaphaelException {
        if (input.isEmpty()) {
            throw new RaphaelException("The command can't be empty!");
        }
        final String[] inputArr = input.split(" ", 2);
        switch(inputArr[0]) {
        case "hello":
            return new HelloCommand();
        case "bye":
            return new ExitCommand();
        case "todo":
            if (inputArr.length == 1 || (inputArr[1] = inputArr[1].trim()).isEmpty()) {
                throw new RaphaelException(RaphaelException.invalidFormat(Command.Type.TODO));
            }
            Task toDo = new Todo(inputArr[1]);
            return new AddCommand(toDo);
        case "deadline":
            if (inputArr.length == 1 || (inputArr[1] = inputArr[1].trim()).isEmpty()) {
                throw new RaphaelException(RaphaelException.invalidFormat(Command.Type.DEADLINE));
            }
            final String[] descriptionDeadline = inputArr[1].split("/by ");
            if (descriptionDeadline.length != 2
                    || (descriptionDeadline[0] = descriptionDeadline[0].trim()).isEmpty()) {
                throw new RaphaelException(RaphaelException.invalidFormat(Command.Type.DEADLINE));
            }
            Task deadline = new Deadline(descriptionDeadline[0], descriptionDeadline[1]);
            return new AddCommand(deadline);
        case "event":
            if (inputArr.length == 1 || (inputArr[1] = inputArr[1].trim()).isEmpty()) {
                throw new RaphaelException(RaphaelException.invalidFormat(Command.Type.EVENT));
            }
            final String[] descriptionTime = inputArr[1].split("/from ");
            if (descriptionTime.length != 2
                    || (descriptionTime[0] = descriptionTime[0].trim()).isEmpty()) {
                throw new RaphaelException(RaphaelException.invalidFormat(Command.Type.EVENT));
            }
            final String[] timeRange = descriptionTime[1].split("/to ");
            if (timeRange.length != 2
                    || (timeRange[0] = timeRange[0].trim()).isEmpty()) {
                throw new RaphaelException(RaphaelException.invalidFormat(Command.Type.EVENT));
            }
            Event event = new Event(descriptionTime[0], timeRange[0], timeRange[1]);
            return new AddCommand(event);
        case "mark":
            return Parser.processTaskWithIndex(inputArr, Command.Type.MARK);
        case "unmark":
            return Parser.processTaskWithIndex(inputArr, Command.Type.UNMARK);
        case "delete":
            return Parser.processTaskWithIndex(inputArr, Command.Type.DELETE);
        case "list":
            return new ListCommand();
        case "any":
            return new MarkCommand();
        case "find":
            if (inputArr.length == 1) {
                throw new raphael.exception.RaphaelException("find [keyword]");
            }
            return new FindCommand(inputArr[1]);
        default:
            throw new RaphaelException("I'm sorry that I can't recognize the command!");
        }
    }
    private static Command processTaskWithIndex(String[] inputArr, Command.Type commandType) throws RaphaelException {
        if (inputArr.length != 2) {
            throw new RaphaelException(RaphaelException.invalidFormat(commandType));
        }
        try {
            int idx = Integer.parseInt(inputArr[1]) - 1;
            if (commandType.equals(Command.Type.DELETE)) {
                return new DeleteCommand(idx);
            }
            return new EditCommand(idx, commandType.equals(Command.Type.MARK));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new RaphaelException(RaphaelException.invalidFormat(commandType));
        }
    }
}
