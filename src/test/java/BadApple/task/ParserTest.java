package BadApple.task;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void invalidTodoCommand() {
        File file = new File("src/main/data/tempFile.txt");

        try {
            Parser.ProcessQuery("todo");
            boolean isEmpty = TaskList.tasks.isEmpty();
            assertTrue(isEmpty);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void invalidCommand() {
        File file = new File("src/main/data/tempFile.txt");

        try {
            int x = TaskList.tasks.size();
            Parser.ProcessQuery("HELLO WORLD");
            int y = TaskList.tasks.size();
            assertEquals(x, y);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void todoCommand() {
        File file = new File("src/main/data/tempFile.txt");

        try {
            Parser.ProcessQuery("todo DONTCARE");
            assertTrue(TaskList.tasks.contains(Todo.extractDetails("todo DONTCARE")));
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void deadlineCommand() {
        File file = new File("src/main/data/tempFile.txt");

        try {
            Parser.ProcessQuery("deadline CS2103 /by 2024-02-06");
            assertEquals("Deadline [] CS2103 (by: 06 Feb 2024 )", TaskList.tasks.get(0).toString());
        } catch (IOException e) {
            fail();
        }
    }
}
