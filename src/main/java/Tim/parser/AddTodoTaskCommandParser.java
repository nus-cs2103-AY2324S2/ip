package Tim.parser;

import Tim.commands.AddTodoTaskCommand;
import Tim.exception.TimException;
import Tim.task.ToDo;

import java.nio.file.Path;

import static Tim.exception.ErrorMessages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input argument and create a new AddTodoTaskCommand object.
 */
public class AddTodoTaskCommandParser implements Parser<AddTodoTaskCommand> {

    /**
     * Parse arguments into AddTodoTaskCommand.
     * @param args contents of Todo task
     * @param filePath file where tasks are to be saved
     * @return AddTodoTaskCommand object
     * @throws TimException
     */
    @Override
    public AddTodoTaskCommand parse(String args, Path filePath) throws TimException {
        assert filePath != null : "File path must not be null";
        if (args.isEmpty()) {
            throw new TimException(MESSAGE_INVALID_COMMAND_FORMAT);
        }
        ToDo todo = new ToDo(args);
        return new AddTodoTaskCommand(todo, filePath);
    }
}
