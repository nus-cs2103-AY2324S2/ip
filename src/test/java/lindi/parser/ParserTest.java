package lindi.parser;

import org.junit.jupiter.api.Test;

import lindi.commands.CreateDeadlineCommand;
import lindi.commands.CreateEventCommand;
import lindi.commands.CreateToDoCommand;
import lindi.commands.DeleteCommand;
import lindi.commands.ExitCommand;
import lindi.commands.FindCommand;
import lindi.commands.InvalidCommand;
import lindi.commands.ListCommand;
import lindi.commands.MarkCommand;
import lindi.commands.UnmarkCommand;

public class ParserTest {
    @Test
    public void parseTestValid() {
        // normal list command returns a ListCommand
        assert(Parser.parse("list") instanceof ListCommand);

        // list command with extra arguments returns a ListCommand
        assert(Parser.parse("list") instanceof ListCommand);

        // normal find command search key argument returns a FindCommand
        assert(Parser.parse("find sampleKey") instanceof FindCommand);

        // normal mark command with index argument returns a MarkCommand
        assert(Parser.parse("mark 3") instanceof MarkCommand);

        // normal mark command with index argument returns a UnmarkCommand
        assert(Parser.parse("unmark 3") instanceof UnmarkCommand);

        // normal delete command with index argument returns a DeleteCommand
        assert(Parser.parse("delete 3") instanceof DeleteCommand);

        // normal todo command with argument returns a CreateToDoCommand
        assert(Parser.parse("todo something") instanceof CreateToDoCommand);

        // normal deadline command with /by argument returns a CreateDeadlineCommand
        assert(Parser.parse("deadline someDeadline /by 2024-02-07-23-59")
                instanceof CreateDeadlineCommand);

        // normal event command with /from and /to argument returns a CreateEventCommand
        assert(Parser.parse("event someEvent /from 2024-02-07-23-59 /to 2024-02-08-23-59")
                instanceof CreateEventCommand);

        // normal bye command returns a ExitCommand
        assert(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parseTestInvalid() {
        // find command without search key argument returns an InvalidCommand
        assert(Parser.parse("find") instanceof InvalidCommand);

        // mark command without index argument returns a InvalidCommand
        assert(Parser.parse("mark") instanceof InvalidCommand);

        // mark command with non-integer argument returns a InvalidCommand
        assert(Parser.parse("mark 3af") instanceof InvalidCommand);

        // unmark command without index argument returns a InvalidCommand
        assert(Parser.parse("unmark") instanceof InvalidCommand);

        // unmark command with non-integer argument returns a InvalidCommand
        assert(Parser.parse("unmark 3af") instanceof InvalidCommand);

        // delete command without index argument returns a InvalidCommand
        assert(Parser.parse("delete") instanceof InvalidCommand);

        // delete command with non-integer argument returns a InvalidCommand
        assert(Parser.parse("delete 3af") instanceof InvalidCommand);
    }
}
