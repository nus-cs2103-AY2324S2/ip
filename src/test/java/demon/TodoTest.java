package demon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {
    @Test
    public void stringMsg_test() {
        String expected = "[T][ ] eat dinner with family on CNY";
        Todo todoTest = new Todo("eat dinner with family on CNY");
        assertEquals(expected, todoTest.toString(),"String returns unexpected result");
    }
}
