package shodan.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import shodan.tasks.impl.Todo;


class TaskSerializerTest {
    @Test
    void serialize_success() {
        List<Task> tasks = new ArrayList<>();

        /* Empty input list */
        assertEquals("", TaskSerializer.serialize(tasks));

        tasks.add(new Todo("test"));
        /* Serializing with one task in the list */
        assertEquals("TODO;false;test;;\n", TaskSerializer.serialize(tasks));
    }

    @Test
    void serialize_delimiterInInput_exceptionThrown() {
        List<Task> tasks = new ArrayList<>();

        /* Serializing task with TaskSerializer.DELIMITER in name field */
        tasks.add(new Todo("te;st"));
        assertThrows(IllegalArgumentException.class, () -> TaskSerializer.serialize(tasks));
    }

    @Test
    void parseText_success() {
        String savedTask = "TODO;false;read book;;\n";
        assertEquals("[[T][ ] read book]", TaskSerializer.parseText(savedTask.lines()).toString());
    }

    @Test
    void parseText_ignoreEmptyLines() {
        String savedTask = "\n\nTODO;false;read book;;\n\n";
        assertEquals("[[T][ ] read book]", TaskSerializer.parseText(savedTask.lines()).toString());
    }

    @Test
    void parseText_ignoreMalformedTasks() {
        String savedTask = "\n\nTODO;fase;read book;;\nODO;false;return book;;\n";
        assertEquals("[]", TaskSerializer.parseText(savedTask.lines()).toString());
    }
}
