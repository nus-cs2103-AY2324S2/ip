package hirwan;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class TasklistTest {
    @Test
    public void delete_outOfBoundIndex_exceptionThrown(){
        try {
            List<String> List = new ArrayList<>();
            List.add(". [T][ ] buy groceries");
            Tasklist listTask = new Tasklist(List);
            listTask.delete(3);
        } catch (Exception e) {
            assertEquals("Index 3 out of bounds for length 1", e.getMessage());
        }
    }

//    @Test
//    public void printList() {
//
//        assertEquals(4, 4);
//    }
}
