package duke;

import java.util.Scanner;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {

    @Test
    public void testcase(){
        // normal division results in an integer answer 2
        assertEquals(null, new Parser(new Scanner(System.in), new TaskList(new ArrayList<Task>()),new Storage("./data/tasks.txt")).dateConvert("hi"));

    }
}
