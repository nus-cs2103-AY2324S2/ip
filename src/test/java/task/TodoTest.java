package task;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void testToString() {
        Todo todoWithDescription = new Todo("test todo description");
        String tString1 = todoWithDescription.toString();
        assertEquals("[T][ ] test todo description", tString1);

        Todo todoWithoutDescription = new Todo("");
        String tString2 = todoWithoutDescription.toString();
        assertEquals("[T][ ] ", tString2);
    }
}
