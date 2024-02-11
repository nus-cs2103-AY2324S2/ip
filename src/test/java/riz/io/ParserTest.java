package riz.io;

import java.util.Scanner;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import riz.data.*;

public class ParserTest {
    private final Scanner scanner = new Scanner(System.in);
    private final TaskList taskList = new TaskList(new ArrayList<>());
    private final Storage storage = new Storage("riz/data/parsertest.txt");

    @Test
    public void parseTest() {
        //taskList.add(new ToDo("shower"));
        //taskList.add(new Deadline("birthday", "19/06/2001 1800"));
        Parser.parse(this.taskList, this.storage,"todo shower");
        Parser.parse(this.taskList, this.storage, "deadline birthday /by 19/06/2001 1800");
        assertEquals("T |   | shower", taskList.get(0).toString());
        assertEquals("D |   | birthday | 19 Jun 2001 06:00 pm", taskList.get(1).toString());
        Parser.parse(this.taskList, this.storage, "mark 1");
        assertEquals("T | X | shower", taskList.get(0).toString());
        Parser.parse(this.taskList, this.storage, "unmark 1");
        assertEquals("T |   | shower", taskList.get(0).toString());
    }
}
