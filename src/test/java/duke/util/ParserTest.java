package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.ByeCommand;
import duke.command.DeleteTaskCommand;
import duke.command.ListTaskCommand;
import duke.command.NoActionCommand;
import duke.command.ToggleMarkTaskCommand;
public class ParserTest {

    @Test
    public void testParse() throws NoSuchFieldException, IllegalAccessException {
        //Test set up
        String todo = "todo read book";
        String deadline = "deadline math assignment about linear algebra /by 2024/03/03 2359";
        String event = "event career fair for undergraduates /from 2024/04/03 1100 /to 2024/04/03 1700";
        String list = "list";
        String mark3 = "mark 3";
        String unmark1 = "unmark 1";
        String delete = "delete 1";
        String gibberish = "I like coding, do you?";
        String bye = "bye";
        LocalDateTime deadlineDate = LocalDateTime.of(2024, 3, 3, 23, 59);
        LocalDateTime eventFromDate = LocalDateTime.of(2024, 4, 3, 11, 0);
        LocalDateTime eventToDate = LocalDateTime.of(2024, 4, 3, 17, 0);
        Parser p = new Parser(new Ui());

        //test 1
        AddTodoCommand dummy1 = (AddTodoCommand) p.parse(todo);
        Field field = AddTodoCommand.class.getDeclaredField("description");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        String todoDes = (String) field.get(dummy1);
        assertEquals(dummy1.getType(), Parser.Cmd.todo);
        assertEquals(todoDes, "read book");

        //test2
        AddDeadlineCommand dummy2 = (AddDeadlineCommand) p.parse(deadline);
        field = AddDeadlineCommand.class.getDeclaredField("description");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        String deadlineDes = (String) field.get(dummy2);
        field = AddDeadlineCommand.class.getDeclaredField("date");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        LocalDateTime deadlineDt = (LocalDateTime) field.get(dummy2);
        assertEquals(dummy2.getType(), Parser.Cmd.deadline);
        assertEquals(deadlineDes, "math assignment about linear algebra");
        assertEquals(deadlineDt, deadlineDate);

        //test3
        AddEventCommand dummy3 = (AddEventCommand) p.parse(event);
        field = AddEventCommand.class.getDeclaredField("description");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        String eventDes = (String) field.get(dummy3);
        field = AddEventCommand.class.getDeclaredField("fromDate");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        LocalDateTime eventFD = (LocalDateTime) field.get(dummy3);
        field = AddEventCommand.class.getDeclaredField("toDate");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        LocalDateTime eventTD = (LocalDateTime) field.get(dummy3);
        assertEquals(dummy2.getType(), Parser.Cmd.deadline);
        assertEquals(eventDes, "career fair for undergraduates");
        assertEquals(eventFD, eventFromDate);
        assertEquals(eventTD, eventToDate);

        //test4
        ListTaskCommand dummy4 = (ListTaskCommand) p.parse(list);
        assertEquals(dummy4.getType(), Parser.Cmd.list);

        //test5
        ToggleMarkTaskCommand dummy5 = (ToggleMarkTaskCommand) p.parse(mark3);
        field = ToggleMarkTaskCommand.class.getDeclaredField("index");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        int markInd = (int) field.get(dummy5);
        assertEquals(dummy5.getType(), Parser.Cmd.mark);
        assertEquals(markInd, 3);

        //test6
        ToggleMarkTaskCommand dummy6 = (ToggleMarkTaskCommand) p.parse(unmark1);
        int unmarkInd = (int) field.get(dummy6);
        assertEquals(dummy6.getType(), Parser.Cmd.unmark);
        assertEquals(unmarkInd, 1);

        //test7
        DeleteTaskCommand dummy7 = (DeleteTaskCommand) p.parse(delete);
        field = DeleteTaskCommand.class.getDeclaredField("index");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        int deleteInd = (int) field.get(dummy7);
        assertEquals(dummy7.getType(), Parser.Cmd.delete);
        assertEquals(deleteInd, 1);

        //test8
        NoActionCommand dummy8 = (NoActionCommand) p.parse(gibberish);
        assertEquals(dummy8.getType(), Parser.Cmd.none);

        //test9
        ByeCommand dummy9 = (ByeCommand) p.parse(bye);
        assertEquals(dummy9.getType(), Parser.Cmd.bye);
    }
}
