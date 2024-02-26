package dude.Tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodoTest {

    @Test
    public void testToString() {
        Todo todo = new Todo("test");
        assertEquals(todo.toString(), "[T][ ] test");
    }

    @Test
    public void testEqualsForSameDescription() {
        Todo todo = new Todo("test description");
        Todo todo2 = new Todo("test description");
        assertEquals(todo, todo2);
    }

    @Test
    public void testEqualsForDifferentDescription() {
        Todo todo = new Todo("test description");
        Todo todo2 = new Todo("other description");
        assertNotEquals(todo, todo2);
    }
}
