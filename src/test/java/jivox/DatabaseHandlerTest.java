package jivox;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jivox.exception.JivoxDatabaseException;
import jivox.task.Deadline;
import jivox.task.Event;
import jivox.task.Tag;
import jivox.task.Task;
import jivox.task.TaskList;
import jivox.task.Todo;



class DatabaseHandlerTest {

    private final String testFile = "./test.txt";
    private DatabaseHandler handler;

    @BeforeEach
    void setUp() {
        handler = new DatabaseHandler(testFile);
    }

    @AfterEach
    void tearDown() {
        File file = new File(testFile);
        file.delete();
    }

    @Test
    void testCreate() throws JivoxDatabaseException {
        handler.create();
        Path path = Path.of(testFile);
        assertTrue(Files.exists(path));
    }

    @Test
    void testSaveAndLoad() throws Exception {
        TaskList tasks = new TaskList(new ArrayList<>());
        Todo todo = new Todo("Todo 1");
        todo.setTag(new Tag("Personal"));
        tasks.add(todo);

        Deadline deadline = new Deadline("Deadline 1", LocalDateTime.now());
        tasks.add(deadline);

        Event event = new Event("Event 1", LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        tasks.add(event);

        handler.save(tasks);

        ArrayList<Task> loaded = handler.load();

        assertEquals(3, loaded.size());
        assertInstanceOf(Todo.class, loaded.get(0));
        assertInstanceOf(Deadline.class, loaded.get(1));
        assertInstanceOf(Event.class, loaded.get(2));
    }

}
