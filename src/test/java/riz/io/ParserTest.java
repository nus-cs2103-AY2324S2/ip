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
        taskList.clearList();
        Parser.parse(this.taskList, this.storage,"todo shower");
        Parser.parse(this.taskList, this.storage, "deadline birthday /by 19/06/2001 1800");
        Parser.parse(this.taskList, this.storage, "event staycation /from 17/06/2022 1500 /to 19/06/2022 1200");
        assertEquals("T |   | shower", taskList.get(0).toString());
        assertEquals("D |   | birthday | 19 Jun 2001 06:00 PM", taskList.get(1).toString());
        assertEquals("E |   | staycation | 17 Jun 2022 03:00 PM - 19 Jun 2022 12:00 PM", taskList.get(2).toString());
        Parser.parse(this.taskList, this.storage, "mark 1");
        assertEquals("T | X | shower", taskList.get(0).toString());
        Parser.parse(this.taskList, this.storage, "unmark 1");
        assertEquals("T |   | shower", taskList.get(0).toString());
    }
}
