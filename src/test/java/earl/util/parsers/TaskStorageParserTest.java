package earl.util.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import earl.exceptions.ParserException;
import earl.tasks.Task;

class TaskStorageParserTest {

    @Test
    void parse_normalEntry_taskReturnedSuccess() throws Exception {
        Task task = TaskStorageParser.parse("TODO,X,test");
        assertNotNull(task);
        assertEquals("[T][X] test", task.toString());
    }

    @Test
    void parse_malformedEntry_exceptionThrown() {
        try {
            TaskStorageParser.parse("TASK, ,test");
            fail();
        } catch (Exception e) {
            assertInstanceOf(ParserException.class, e);
        }
    }
}
