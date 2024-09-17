package lucky.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class ParseCommandTest {

    @Test
    public void parse_viewListCommand_success() throws IOException {
        String[] input = {"list"};

        Command result = ParseCommand.parse(input);

        assertTrue(result instanceof ViewTaskListCommand);
    }

    @Test
    public void parse_exitCommand_success() throws IOException {
        String[] input = {"bye"};

        Command result = ParseCommand.parse(input);

        assertTrue(result instanceof ExitCommand);
    }

    @Test
    public void parse_markCommand_success() throws IOException {
        String[] input = {"mark", "1"};

        Command result = ParseCommand.parse(input);

        assertTrue(result instanceof MarkCommand);
    }

    @Test
    public void parse_unmarkCommand_success() throws IOException {
        String[] input = {"unmark", "1"};

        Command result = ParseCommand.parse(input);

        assertTrue(result instanceof UnmarkCommand);
    }

    @Test
    public void parse_addToDoCommand_success() throws IOException {
        String[] input = {"todo", "Buy groceries"};

        Command result = ParseCommand.parse(input);

        assertTrue(result instanceof AddToDoCommand);
    }

    @Test
    public void parse_addDeadlineCommand_success() throws IOException {
        String[] input = {"deadline", "Submit report /by 2024/12/31"};

        Command result = ParseCommand.parse(input);

        assertTrue(result instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_addEventCommand_success() throws IOException {
        String[] input = {"event", "Movie night /from 2022/12/31 18:00 /to 2022-12-31 22:00"};

        Command result = ParseCommand.parse(input);

        assertTrue(result instanceof AddEventCommand);
    }

    @Test
    public void parse_deleteCommand_success() throws IOException {
        String[] input = {"delete", "1"};

        Command result = ParseCommand.parse(input);

        assertTrue(result instanceof DeleteCommand);
    }

    @Test
    public void parse_defaultCommand_success() throws IOException {
        String[] input = {"unknownCommand"};

        Command result = ParseCommand.parse(input);

        assertTrue(result instanceof Command);
    }
}
