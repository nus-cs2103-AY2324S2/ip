package drew.storage;
import drew.command.Command;
import drew.ui.Parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void checkCommandIdentity_allTask_assertionSucceed() {
        Parser parser = new Parser();

        String todoTask = "todo homework";
        Command exepectedTodo = Command.TODO;
        Command actualTodo = parser.checkCommandIdentity(todoTask);
        assertEquals(exepectedTodo, actualTodo);

        String deadline = "deadline homework /by 2024-02-05";
        Command actualDeadline = parser.checkCommandIdentity(deadline);
        assertEquals(Command.DEADLINE, actualDeadline);

        String event = "event concert /from 2024-05-15 /to 2024-05-10";
        Command actualEvent = parser.checkCommandIdentity(event);
        assertEquals(Command.EVENT, actualEvent);

        String list = "list";
        Command expectedList = Command.LIST;
        Command actualList = parser.checkCommandIdentity(list);
        assertEquals(expectedList, actualList);

        String mark = "mark 1";
        Command actualMark = parser.checkCommandIdentity(mark);
        assertEquals(Command.MARK, actualMark);

        String unmark = "unmark 1";
        Command acutalUnmark = parser.checkCommandIdentity(unmark);
        assertEquals(Command.UNMARK, acutalUnmark);
    }
}
