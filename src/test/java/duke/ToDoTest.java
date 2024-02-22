package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void addToDoTaskTest() throws GeorgieException {
        TaskList taskList = new TaskList();
        ToDo.addToDoTask(taskList, "Test ToDo Task");
        assertEquals("[T] [ ] Test ToDo Task", taskList.getTask(0).getStatusIcon());
    }

    @Test
    public void getStatusIconTest() {
        ToDo todo = new ToDo("Test Todo");

        assertEquals("[T] [ ] Test Todo",
                todo.getStatusIcon().replaceAll("\\s+", " "));
    }

    @Test
    public void toFileStringTest() {
        ToDo todo = new ToDo("Test Todo");

        assertEquals("T | 0 | Test Todo", todo.toFileString());
    }

    @Test
    public void getStatusIconWithEmptyDescriptionTest() {
        ToDo todo = new ToDo("");

        assertEquals("[T] [ ] ", todo.getStatusIcon().replaceAll("\\s+", " "));
    }

}
