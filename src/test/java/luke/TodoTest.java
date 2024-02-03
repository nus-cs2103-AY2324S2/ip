package luke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    //https://se-education.org/se-book/cppToJava/junit/basic/index.html for test naming ideas


    @Test
    public void testFullStatus() {
        Todo stuff = new Todo("stuff");
        Todo things = new Todo("things");
        assertEquals(stuff.fullStatus(), "[T][ ] stuff");
        assertEquals(things.fullStatus(), "[T][ ] things");
    }

    @Test
    public void markTasks() {
        Todo stuff = new Todo("stuff");
        Todo things = new Todo("things");
        stuff.complete();
        things.complete();
        assertEquals(stuff.fullStatus(), "[T][X] stuff");
        assertEquals(things.fullStatus(), "[T][X] things");
    }
}
