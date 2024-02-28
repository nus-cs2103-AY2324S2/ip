import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import parser.ParseExecutionable;
import parser.Parser;
import task.Task;
import task.TaskStorage;
import task.ToDo;

public class ParserTest {

    @Test
    public void parseLine_validToDoTask_success() {
        Parser parser = new Parser();
        TaskStorage taskStorage = new TaskStorage();
        ParseExecutionable parseExecutionable = parser.parseInput("todo Homework!");
        parseExecutionable.execute(taskStorage);
        assertEquals(taskStorage.size(), 1);
    }

    @Test
    public void parseLine_validDeadlineTask_success() {
        Parser parser = new Parser();
        TaskStorage taskStorage = new TaskStorage();
        ParseExecutionable parseExecutionable =
                parser.parseInput("deadline cs2103T Homework /by 2025-03-01 2359");
        parseExecutionable.execute(taskStorage);
        assertEquals(taskStorage.size(), 1);
    }

    @Test
    public void parseLine_validEventTask_success() {
        Parser parser = new Parser();
        TaskStorage taskStorage = new TaskStorage();
        ParseExecutionable parseExecutionable =
                parser.parseInput("event cs2103t lecture /from 2025-03-01 1600 /to 2024-03-01 1800");
        parseExecutionable.execute(taskStorage);
        assertEquals(taskStorage.size(), 1);
    }

    @Test
    public void parseLine_validTaskandCompleted_success() {
        Parser parser = new Parser();
        TaskStorage taskStorage = new TaskStorage();
        Task task = new ToDo("Homework Test");
        taskStorage.addTask(task);
        ParseExecutionable parseExecutionable = parser.parseInput("mark 1");
        parseExecutionable.execute(taskStorage);
        assertEquals(taskStorage.toString(), "You have 1 tasks, they are:\n1.[T][X] Homework Test");
    }

    @Test
    public void parseLine_validTaskMarkandUnMark_success() {
        Parser parser = new Parser();
        TaskStorage taskStorage = new TaskStorage();
        Task task = new ToDo("Homework Test");
        taskStorage.addTask(task);
        ParseExecutionable parseExecutionable = parser.parseInput("mark 1");
        parseExecutionable.execute(taskStorage);
        parseExecutionable = parser.parseInput("unmark 1");
        parseExecutionable.execute(taskStorage);
        assertEquals(taskStorage.toString(), "You have 1 tasks, they are:\n1.[T][ ] Homework Test");
    }

    @Test
    public void parseLine_validTaskandDelete_success() {
        Parser parser = new Parser();
        TaskStorage taskStorage = new TaskStorage();
        Task task = new ToDo("Homework Test Delete");
        taskStorage.addTask(task);
        ParseExecutionable parseExecutionable = parser.parseInput("delete 1");
        parseExecutionable.execute(taskStorage);
        assertEquals(taskStorage.size(), 0);
    }

    @Test
    public void parseLine_sortTask_success() {
        Parser parser = new Parser();
        TaskStorage taskStorage = new TaskStorage();
        ParseExecutionable parseExecutionable = parser.parseInput("todo B Homework!");
        parseExecutionable.execute(taskStorage);
        parseExecutionable = parser.parseInput("todo A Homework!");
        parseExecutionable.execute(taskStorage);
        parseExecutionable = parser.parseInput("sort");
        parseExecutionable.execute(taskStorage);
        ArrayList<Task> storageList = taskStorage.getStorage();
        assertEquals(storageList.get(0).toString(), "[T][ ] A Homework!");
    }
}
