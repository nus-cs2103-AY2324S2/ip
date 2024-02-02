package duke.util;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testAddListStateRecord() throws NoSuchFieldException, IllegalAccessException {
        //Test set up
        Storage s = new Storage();
        String todo = "todo";
        String deadline = "deadline";
        String event = "event";
        String[] todoData = {"todo description"};
        String[] deadlineData = {"deadline description", "1995/05/23 0000"};
        String[] eventData = {"event description", "1995/05/23 0000", "2023/12/12 0000"};
        
        Field field = Storage.class.getDeclaredField("listStates");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        ArrayList<String> val = (ArrayList<String>) field.get(s);
        // test case 1
        s.addListStateRecord(todo, todoData);
        assertEquals(val.get(0), "T | 0 | todo description");

        // test case 2
        s.addListStateRecord(deadline, deadlineData);
        assertEquals(val.get(1), "D | 0 | deadline description | 1995/05/23 0000");

        // test case 3
        s.addListStateRecord(event, eventData);
        assertEquals(val.get(2), "E | 0 | event description | 1995/05/23 0000 | 2023/12/12 0000");
    }
}
