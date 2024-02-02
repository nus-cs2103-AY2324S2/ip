package duke.util;

import duke.command.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Parser p = new Parser();

        //test 1
        AddTodo dummy1 = (AddTodo) p.parse(todo);
        Field field = AddTodo.class.getDeclaredField("description");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        String todoDes = (String) field.get(dummy1);
        assertEquals(dummy1.getType(), Parser.Cmd.todo);
        assertEquals(todoDes, "read book");

        //test2
        AddDeadline dummy2 = (AddDeadline) p.parse(deadline);
        field = AddDeadline.class.getDeclaredField("description");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        String deadlineDes = (String) field.get(dummy2);
        field = AddDeadline.class.getDeclaredField("date");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        LocalDateTime deadlineDt = (LocalDateTime) field.get(dummy2);
        assertEquals(dummy2.getType(), Parser.Cmd.deadline);
        assertEquals(deadlineDes, "math assignment about linear algebra");
        assertEquals(deadlineDt, deadlineDate);

        //test3
        AddEvent dummy3 = (AddEvent) p.parse(event);
        field = AddEvent.class.getDeclaredField("description");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        String eventDes = (String) field.get(dummy3);
        field = AddEvent.class.getDeclaredField("fromDate");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        LocalDateTime eventFD = (LocalDateTime) field.get(dummy3);
        field = AddEvent.class.getDeclaredField("toDate");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        LocalDateTime eventTD = (LocalDateTime) field.get(dummy3);
        assertEquals(dummy2.getType(), Parser.Cmd.deadline);
        assertEquals(eventDes, "career fair for undergraduates");
        assertEquals(eventFD, eventFromDate);
        assertEquals(eventTD, eventToDate);

        //test4
        ListTask dummy4 = (ListTask) p.parse(list);
        assertEquals(dummy4.getType(), Parser.Cmd.list);

        //test5
        ToggleMarkTask dummy5 = (ToggleMarkTask) p.parse(mark3);
        field = ToggleMarkTask.class.getDeclaredField("index");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        int markInd = (int) field.get(dummy5);
        assertEquals(dummy5.getType(), Parser.Cmd.mark);
        assertEquals(markInd, 3);

        //test6
        ToggleMarkTask dummy6 = (ToggleMarkTask) p.parse(unmark1);
        int unmarkInd =(int) field.get(dummy6);
        assertEquals(dummy6.getType(), Parser.Cmd.unmark);
        assertEquals(unmarkInd, 1);

        //test7
        DeleteTask dummy7 = (DeleteTask) p.parse(delete);
        field = DeleteTask.class.getDeclaredField("index");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        int deleteInd = (int) field.get(dummy7);
        assertEquals(dummy7.getType(), Parser.Cmd.delete);
        assertEquals(deleteInd, 1);

        //test8
        NoAction dummy8 = (NoAction) p.parse(gibberish);
        assertEquals(dummy8.getType(), Parser.Cmd.none);

        //test9
        ByeCommand dummy9 = (ByeCommand) p.parse(bye);
        assertEquals(dummy9.getType(), Parser.Cmd.bye);
    }
}
