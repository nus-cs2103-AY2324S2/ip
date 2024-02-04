/*package riz.io;

import java.util.Scanner;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import riz.data.*;

public class ParserTest {
    private final Scanner scanner = new Scanner(System.in);
    private TaskList taskList = new TaskList(new ArrayList<>());
    private Storage storage = new Storage("riz/data/parsertest.txt");

    @Test
    public void parseTest() {
        taskList.add(new ToDo("shower"));
        assertEquals("added: " + "T |  | shower...\n"
        + "You currently have 1 things to do...", Parser.parse(scanner, this.taskList, storage, "todo shower"));
    }
}
*/