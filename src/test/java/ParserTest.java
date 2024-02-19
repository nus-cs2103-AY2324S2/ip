import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import parser.ParseExecutionable;
import parser.Parser;
import task.Task;
import task.TaskStorage;
import task.ToDo;

public class ParserTest {

    @Test
    public void parseLine_validTask_success() {
        Parser parser = new Parser();
        TaskStorage taskStorage = new TaskStorage();
        ParseExecutionable parseExecutionable = parser.parseInput("todo Homework!");
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
        assertEquals(taskStorage.toString(), "You have 1 tasks, they are:\n1." + task.toString());
    }
}
