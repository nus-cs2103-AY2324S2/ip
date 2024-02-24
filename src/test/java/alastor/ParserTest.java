package alastor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import alastor.task.Deadline;
import alastor.task.Event;
import alastor.task.Task;
import alastor.task.ToDo;

public class ParserTest {

    @Test
    public void parseFile_intactFile() {
        try {
            ArrayList<Task> list = new ArrayList<>();
            Parser.parseFile("T | 0 | read book", list, 0);
            assertEquals(1, list.size());
            ToDo todo = new ToDo("read book");
            assertEquals(todo, list.get(0));
        } catch (AlastorException e) {
            fail();
        }

        try {
            ArrayList<Task> list = new ArrayList<>();
            Parser.parseFile("T | 1 | read book", list, 0);
            assertEquals(1, list.size());
            ToDo todo = new ToDo("read book");
            todo.mark();
            assertEquals(todo, list.get(0));
        } catch (AlastorException e) {
            fail();
        }

        try {
            ArrayList<Task> list = new ArrayList<>();
            Parser.parseFile("D | 0 | return book | 01-01-2021 18:00", list, 0);
            assertEquals(1, list.size());
            Deadline deadline = new Deadline("return book",
                    Parser.stringToDateTime("01-01-2021 18:00", Parser.ParseType.FILE));
            assertEquals(deadline, list.get(0));
        } catch (AlastorException e) {
            fail();
        }

        try {
            ArrayList<Task> list = new ArrayList<>();
            Parser.parseFile("E | 0 | project meeting | 01-01-2021 18:00 | 01-01-2021 20:00", list, 0);
            assertEquals(1, list.size());
            Event event = new Event("project meeting",
                    Parser.stringToDateTime("01-01-2021 18:00", Parser.ParseType.FILE),
                    Parser.stringToDateTime("01-01-2021 20:00", Parser.ParseType.FILE));
            assertEquals(event, list.get(0));
        } catch (AlastorException e) {
            fail();
        }
    }

    @Test
    public void parseFile_corruptedFile() {
        Parser.ParseType type = Parser.ParseType.FILE;

        try {
            ArrayList<Task> list = new ArrayList<>();
            Parser.parseFile("incorrect type | 0 | task", list, 0);
            fail();
        } catch (AlastorException e) {
            assertEquals(type.getErrorMessage() + "There might be some invalid task types or separators",
                    e.getMessage());
        }

        try {
            ArrayList<Task> list = new ArrayList<>();
            Parser.parseFile("T | incorrect value | task", list, 0);
            fail();
        } catch (AlastorException e) {
            assertEquals(type.getErrorMessage() + "There might be some invalid marking values",
                    e.getMessage());
        }

        try {
            ArrayList<Task> list = new ArrayList<>();
            Parser.parseFile("T | 0 | ", list, 0);
            fail();
        } catch (AlastorException e) {
            assertEquals(type.getErrorMessage() + "There might be some empty parameters",
                    e.getMessage());
        }

        try {
            ArrayList<Task> list = new ArrayList<>();
            Parser.parseFile("T | 0 | task | 01-01-2021 18:00", list, 0);
            fail();
        } catch (AlastorException e) {
            assertEquals(type.getErrorMessage() + "There might be some extra invalid parameters",
                    e.getMessage());
        }

        try {
            ArrayList<Task> list = new ArrayList<>();
            Parser.parseFile("D | 0 | task | wrong date-time format", list, 0);
            fail();
        } catch (AlastorException e) {
            assertEquals(type.getErrorMessage() + "The date and time might be in an incorrect format,"
                    + " it should be dd-MM-yyyy HH:mm", e.getMessage());
        }

        try {
            ArrayList<Task> list = new ArrayList<>();
            Parser.parseFile("T | 0 ", list, 0);
            fail();
        } catch (AlastorException e) {
            assertEquals(type.getErrorMessage()
                    + "There might be some missing parameters or invalid separators", e.getMessage());
        }

        try {
            ArrayList<Task> list = new ArrayList<>();
            Parser.parseFile("T : 0 : task ", list, 0);
            fail();
        } catch (AlastorException e) {
            assertEquals(type.getErrorMessage() + "There might be some invalid task types or separators",
                    e.getMessage());
        }
    }
}
