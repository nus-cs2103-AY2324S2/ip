package hal.codec;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import hal.tasks.Deadline;
import hal.tasks.Event;
import hal.tasks.Task;
import hal.tasks.Todo;
class CodecTest {
    @Test
    void encodeTodo() {
        Codec codec = new Codec();
        Task todo = new Todo("NAME");
        String outputString = codec.encode(todo);
        assertEquals("T, false, NAME", outputString);
    }

    @Test
    void encodeMarkedTodo() {
        Codec codec = new Codec();
        Task todo = new Todo("NAME");
        todo.markDone();
        String outputString = codec.encode(todo);
        assertEquals("T, true, NAME", outputString);
    }
    @Test
    void encodeDeadline() {
        Codec codec = new Codec();
        LocalDateTime timeBy = LocalDateTime.of(2023, 03, 20, 12, 0);
        Task deadline = new Deadline("NAME", timeBy);
        String outputString = codec.encode(deadline);
        assertEquals("D, false, NAME, 20-03-2023 12:00", outputString);
    }
    @Test
    void encodeMarkedDeadline() {
        Codec codec = new Codec();
        LocalDateTime timeBy = LocalDateTime.of(2023, 03, 20, 12, 0);
        Task deadline = new Deadline("NAME", timeBy);
        deadline.markDone();
        String outputString = codec.encode(deadline);
        assertEquals("D, true, NAME, 20-03-2023 12:00", outputString);
    }
    @Test
    void encodeEvent() {
        Codec codec = new Codec();
        LocalDateTime timeFrom = LocalDateTime.of(2023, 03, 20, 12, 0);
        LocalDateTime timeTo = LocalDateTime.of(2023, 03, 20, 20, 0);
        Task event = new Event("NAME", timeFrom, timeTo);
        String outputString = codec.encode(event);
        assertEquals("E, false, NAME, 20-03-2023 12:00, 20-03-2023 20:00", outputString);
    }
    @Test
    void encodeMarkedEvent() {
        Codec codec = new Codec();
        LocalDateTime timeFrom = LocalDateTime.of(2023, 03, 20, 12, 0);
        LocalDateTime timeTo = LocalDateTime.of(2023, 03, 20, 20, 0);
        Task event = new Event("NAME", timeFrom, timeTo);
        event.markDone();
        String outputString = codec.encode(event);
        assertEquals("E, true, NAME, 20-03-2023 12:00, 20-03-2023 20:00", outputString);
    }
    @Test
    void buildString() {
        Codec codec = new Codec();
        String builtString = codec.buildString("This", "is", "the", "built", "string");
        assertEquals("This, is, the, built, string", builtString);
    }
}
