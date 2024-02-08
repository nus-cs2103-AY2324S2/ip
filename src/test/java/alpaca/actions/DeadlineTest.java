package alpaca.actions;

import alpaca.tasks.Task;
import alpaca.exceptions.ValueNotFound;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;;

public class DeadlineTest {
    @Test
    public void noInputTest() {
        ValueNotFound thrown = assertThrows(ValueNotFound.class, () -> Deadline.run("Deadline", new ArrayList<Task>()),
           "Expected ValueNotFound thrown."
    );

    assertTrue(thrown.getMessage().equals("You need to provide a value for deadline"));
    }

    @Test
    public void validInput() {
        try {
            ArrayList<Task> list = new ArrayList<Task>();
            Deadline.run("Deadline Read Book", list);
            assertEquals(list.get(0).getType(), "D");
            assertEquals(list.get(0).getName(), "Read Book");
        } catch(Exception e) {
            System.out.println("Exception was thrown");
        }
    }
}
