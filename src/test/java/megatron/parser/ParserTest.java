package megatron.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import megatron.task.Deadline;
import megatron.task.Event;
import megatron.task.Task;
import megatron.task.ToDo;

/**
 * Test Class for Parser
 */
public class ParserTest {
    @Test
    public void parseDeadlineStorageTest() {
        String testTaskStored = "D;;;0;;;homework :::2023-01-30T18:00";
        Task decodedTask = new Deadline("homework ",
                LocalDateTime.of(2023, 1, 30, 18, 0));
        assertEquals(Parser.parseFromStorage(testTaskStored).toString(), decodedTask.toString());
    }

    @Test
    public void parseToDoStorageTest() {
        String testTaskStored = "T;;;1;;;hi";
        Task decodedTask = new ToDo("hi");
        decodedTask.setMarked();
        assertEquals(Parser.parseFromStorage(testTaskStored).toString(), decodedTask.toString());
    }

    @Test
    public void parseEventStorageTest() {
        String testTaskStored = "E;;;0;;;assignment :::2023-01-30T12:00:::2023-02-12T23:59";
        Task decodedTask = new Event("assignment ",
                LocalDateTime.of(2023, 1, 30, 12, 0),
                LocalDateTime.of(2023, 2, 12, 23, 59));
        assertEquals(Parser.parseFromStorage(testTaskStored).toString(), decodedTask.toString());
    }

}

