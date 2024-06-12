package jmsandiegoo.tyrone.parser;

import jmsandiegoo.tyrone.commands.*;
import jmsandiegoo.tyrone.exceptions.IncorrectCommandException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ParserTest {

    @InjectMocks
    Parser parser;

    @Test
    public void parseRawUserCommand_byeCommand_success() {
        // bye command
        String userInput = "bye";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(ByeCommand.class, command);
        } catch(IncorrectCommandException e) {
            fail();
        }
    }

    @Test
    public void parseRawUserCommand_listCommand_success() {
        // list command
        String userInput = "list";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(ListCommand.class, command);
        } catch(IncorrectCommandException e) {
            fail();
        }
    }

    @Test
    public void parseRawUserCommand_listCommand_failure() {
        // invalid list command
        String userInput = "list invalid params";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(ListCommand.class, command);
            fail();
        } catch(IncorrectCommandException e) {
            // pass
        }
    }

    @Test
    public void parseRawUserCommand_toDoCommand_success() {
        // todo command
        String userInput = "todo read book";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(TodoCommand.class, command);
        } catch(IncorrectCommandException e) {
            fail();
        }
    }

    @Test
    public void parseRawUserCommand_toDoCommand_failure() {
        // invalid todo command
        String userInput = "todo";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(TodoCommand.class, command);
            fail();
        } catch(IncorrectCommandException e) {
            // pass
        }
    }

    @Test
    public void parseRawUserCommand_deadlineCommand_success() {
        // deadline command
        String userInput = "deadline cook food /by 2024-06-05 14:00";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(DeadlineCommand.class, command);
        } catch (IncorrectCommandException e) {
            fail();
        }
    }

    @Test
    public void parseRawUserCommand_deadlineCommand_failure() {
        // invalid deadline command
        String userInput = "deadline cook food /by invalid date";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(DeadlineCommand.class, command);
            fail();
        } catch (IncorrectCommandException e) {
            // pass
        }
    }

    @Test
    public void parseRawUserCommand_eventCommand_success() {
        // event command
        String userInput = "event project meeting /from 2024-06-05 14:00 /to 2024-06-05 16:00";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(EventCommand.class, command);
        } catch (IncorrectCommandException e) {
            fail();
        }
    }

    @Test
    public void parseRawUserCommand_eventCommand_failure() {
        // invalid event command
        String userInput = "event project meeting /from 2024-06-05 14:00";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(EventCommand.class, command);
            fail();
        } catch (IncorrectCommandException e) {
            // pass
        }
    }

    @Test
    public void parseRawUserCommand_markCommand_success() {
        // mark command
        String userInput = "mark 1";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(MarkCommand.class, command);
        } catch (IncorrectCommandException e) {
            fail();
        }
    }

    @Test
    public void parseRawUserCommand_markCommand_failure() {
        // invalid mark command
        String userInput = "mark incorrect params";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(MarkCommand.class, command);
            fail();
        } catch (IncorrectCommandException e) {
            // pass
        }
    }

    @Test
    public void parseRawUserCommand_unmarkCommand_success() {
        // unmark command
        String userInput = "unmark 1";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(UnmarkCommand.class, command);
        } catch (IncorrectCommandException e) {
            fail();
        }
    }

    @Test
    public void parseRawUserCommand_unmarkCommand_failure() {
        // invalid unmark command
        String userInput = "unmark incorrect params";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(UnmarkCommand.class, command);
            fail();
        } catch (IncorrectCommandException e) {
            // pass
        }
    }

    @Test
    public void parseRawUserCommand_deleteCommand_success() {
        // delete command
        String userInput = "delete 1";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(DeleteCommand.class, command);
        } catch (IncorrectCommandException e) {
            fail();
        }
    }

    @Test
    public void parseRawUserCommand_deleteCommand_failure() {
        // delete command
        String userInput = "delete incorrect params";

        // Act
        try {
            Command command = parser.parseRawUserCommand(userInput);
            assertNotNull(command);
            assertInstanceOf(DeleteCommand.class, command);
            fail();
        } catch (IncorrectCommandException e) {
            // pay
        }
    }

    @Test
    public void parseRawUserCommand_invalidCommand_throwException() {
        // Arrange
        String userInput = "invalidCommand";

        // Act & Assert
        assertThrows(IncorrectCommandException.class, () -> parser.parseRawUserCommand(userInput));
    }
}
