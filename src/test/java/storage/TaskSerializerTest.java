package storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class TaskSerializerTest {

    @Test
    public void testSerializeTodo() {
        Todo todo = new Todo("Sample Todo");
        todo.setStatus(true); // Completed
        
        String serializedTodo = TaskSerializer.serialize(List.of(todo));
        assertEquals("T, 1, Sample Todo, , \n", serializedTodo);
    }
    
    @Test
    public void testSerializeDeadline() {
        Deadline deadline = new Deadline("Sample Deadline", LocalDateTime.parse("2/12/2019 1800", Task.INPUT_DATE_FORMAT));
        
        String serializedDeadline = TaskSerializer.serialize(List.of(deadline));
        assertEquals("D, 0, Sample Deadline, , 2019-12-02T18:00\n", serializedDeadline);
    }
    
    @Test
    public void testSerializeEvent() {
        Event event = new Event("Sample Event", 
                                    LocalDateTime.parse("2/12/2019 1200", Task.INPUT_DATE_FORMAT), 
                                    LocalDateTime.parse("2/12/2019 1400", Task.INPUT_DATE_FORMAT));
        
        String serializedEvent = TaskSerializer.serialize(List.of(event));
        assertEquals("E, 0, Sample Event, 2019-12-02T12:00, 2019-12-02T14:00\n", serializedEvent);
    }
    
    @Test
    public void testParseTextTodo() {
        String serializedTodo = "T, 1, Sample Todo";
        Task parsedTask = TaskSerializer.parseText(serializedTodo);
        
        assertTrue(parsedTask instanceof Todo);
        assertEquals("Sample Todo", parsedTask.getDescription());
        assertTrue(parsedTask.getStatus());
    }
    
    @Test
    public void testParseTextDeadline() {
        String serializedDeadline = "D, 0, Sample Deadline, , 2023-12-02T18:00";
        Task parsedTask = TaskSerializer.parseText(serializedDeadline);
        
        assertTrue(parsedTask instanceof Deadline);
        assertEquals("Sample Deadline", parsedTask.getDescription());
        assertFalse(parsedTask.getStatus());
        assertEquals("Dec 02 2023 06:00 pm", ((Deadline) parsedTask).getDueDate().format(Task.OUTPUT_DATE_FORMAT));
    }
    
    @Test
    public void testParseTextEvent() {
        String serializedEvent = "E, 0, Sample Event, 2023-02-12T12:00, 2023-02-12T14:00";
        Task parsedTask = TaskSerializer.parseText(serializedEvent);
        
        assertTrue(parsedTask instanceof Event);
        assertEquals("Sample Event", parsedTask.getDescription());
        assertFalse(parsedTask.getStatus());
        assertEquals("Feb 12 2023 12:00 pm", ((Event) parsedTask).getStartDate().format(Task.OUTPUT_DATE_FORMAT));
        assertEquals("Feb 12 2023 02:00 pm", ((Event) parsedTask).getEndDate().format(Task.OUTPUT_DATE_FORMAT));
    }
}
