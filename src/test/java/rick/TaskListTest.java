package rick;

import org.junit.jupiter.api.Test;
import rick.tasks.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void listTest () {
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            try {
                list.add(new ToDo("todo", "[ ]"));
            } catch (RickException e) {
                System.out.println(e.getMessage());
            }
        }
        String target = "____________________________________________________________\n" +
                "1. [T][ ] todo\n" +
                "2. [T][ ] todo\n" +
                "3. [T][ ] todo\n" +
                "4. [T][ ] todo\n" +
                "5. [T][ ] todo\n" +
                "____________________________________________________________";
        assertEquals(target, new TaskList(list).list());
    }
    @Test
    public void addToListTest () {
        ArrayList<Item> list = new ArrayList<>();
        TaskList taskList = new TaskList(list);
        Storage storage = new Storage("data/test.txt");
        try {
            storage.load();
        } catch (RickException e) {
            System.out.println(e.getMessage());
        }
        String out1 = "";
        String out2 = "";
        String out3 = "";

        try {
            out1 = taskList.addToList("todo readbook", storage);
            out2 = taskList.addToList("deadline wa1 /by 2024-02-09T23:59:00", storage);
            out3 = taskList.addToList("event meeting /from 2024-02-09 /to 2024-02-10", storage);
        } catch (RickException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<Item> targetList = new ArrayList<>();
        //Testing for correctly returing reply
        String output1 = "Got it. I've added this task:\n" +
                "[T][ ] readbook" +
                "\nNow you have 1 tasks in the list.";
        String output2 = "Got it. I've added this task:\n" +
                "[D][ ] wa1 (by: Feb 09 2024, 23:59:00)" +
                "\nNow you have 2 tasks in the list.";
        String output3 = "Got it. I've added this task:\n" +
                "[E][ ] meeting (from: Feb 09 2024 to: Feb 10 2024)" +
                "\nNow you have 3 tasks in the list.";
        assertEquals(output1, out1);
        assertEquals(output2, out2);
        assertEquals(output3, out3);
        //Testing for adding to the list correctly
        //TODO require using list() method. Still fall under unit testing?
        //TODO Testing for exceptions
    }

    @Test
    public void findTest () {
        ArrayList<Item> arrayList = new ArrayList<>();
        try {
            arrayList.add(new ToDo("todo", "[ ]"));
            arrayList.add(new ToDo("todo1", "[ ]"));
            arrayList.add(new ToDo("todo2", "[ ]"));
            arrayList.add(new ToDo("readbook", "[ ]"));
        } catch (RickException e) {
            System.out.println(e.getMessage());
        }
        TaskList taskList = new TaskList(arrayList);
        String output = taskList.find("todo");
        String expected = "Here are the matching tasks in your list:\n" +
                "1. [T][ ] todo\n" +
                "2. [T][ ] todo1\n" +
                "3. [T][ ] todo2\n";
        assertEquals(expected, output);
    }
}
