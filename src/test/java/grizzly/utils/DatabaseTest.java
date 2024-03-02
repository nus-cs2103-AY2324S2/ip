package grizzly.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import grizzly.contacts.Contact;
import grizzly.tasks.Task;
import grizzly.tasks.Todo;

public class DatabaseTest {
    @Test
    public void addTaskTest() {
        Database db = new Database();
        Task t = new Task(false, "test");
        db.addTask(t);
        assertEquals(db.taskListSize(), 1);
    }

    @Test
    public void addTodoTest() {
        Database db = new Database();
        Task t = new Todo(false, "test");
        db.addTask(t);
        assertEquals(db.tasksToString(), ("----------\nTasks\n----------\n1.[T][ ] test\n"));
    }

    @Test
    public void addContactTest() {
        Database db = new Database();
        Contact c = new Contact("test", "something@something", 123231313);
        db.addContact(c);
        assertEquals(db.contactListSize(), 1);
        assertEquals(db.contactsToString(), ("----------\nContacts\n----------\n"
                                                + "1.test | something@something | 123231313\n"));
    }

    @Test
    public void databaseToStringTest() {
        Database db = new Database();
        Task t = new Todo(false, "test");
        db.addTask(t);
        Contact c = new Contact("test", "something@something", 123231313);
        db.addContact(c);
        assertEquals(db.toString(), ("----------\nTasks\n----------\n1.[T][ ] test\n"
                                        + "----------\nContacts\n----------\n"
                                        + "1.test | something@something | 123231313\n"));
    }
}
