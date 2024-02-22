package gronk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class UserInterfaceTest {
    @Test
    public void returnTodoTest() {
        TaskList t = new TaskList();
        UserInterface ui = new UserInterface();
        t.addTask(new Todo("run", 0));
        t.addTask(new Todo("jump", 0));
        t.addTask(new Todo("swim", 0));
        String expected = "1. [T] [ ] run\n"
                + "2. [T] [ ] jump\n"
                + "3. [T] [ ] swim";
        assertEquals(ui.returnAllTasks(t), expected);
    }

    @Test
    public void returnDeadlineTest() {
        TaskList t = new TaskList();
        UserInterface ui = new UserInterface();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ld = LocalDate.parse("17/01/2024", df);
        t.addTask(new Deadline("run", 0, ld));
        t.addTask(new Deadline("jump", 1, ld));
        t.addTask(new Deadline("swim", 0, ld));
        String expected = "1. [D] [ ] run (by: 17/01/2024)\n"
                + "2. [D] [X] jump (by: 17/01/2024)\n"
                + "3. [D] [ ] swim (by: 17/01/2024)";
        assertEquals(ui.returnAllTasks(t), expected);
    }

    @Test
    public void returnEventTest() {
        TaskList t = new TaskList();
        UserInterface ui = new UserInterface();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ld = LocalDate.parse("17/01/2024", df);
        LocalDate ld2 = LocalDate.parse("20/01/2024", df);
        t.addTask(new Event("run", 0, ld, ld2));
        t.addTask(new Event("jump", 1, ld, ld2));
        t.addTask(new Event("swim", 1, ld, ld2));
        String expected = "1. [E] [ ] run (during: 17/01/2024-20/01/2024)\n"
                + "2. [E] [X] jump (during: 17/01/2024-20/01/2024)\n"
                + "3. [E] [X] swim (during: 17/01/2024-20/01/2024)";
        assertEquals(ui.returnAllTasks(t), expected);
    }

    @Test
    public void returnEmptyTasksTest() {
        TaskList t = new TaskList();
        UserInterface ui = new UserInterface();
        String expected = "Nothing added yet!";
        assertEquals(ui.returnAllTasks(t), expected);
    }
}
