package parser;
import exception.TobiasException;
import org.junit.jupiter.api.Test;
import task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void no_from_and_to_for_events() {
        TaskList testList = new TaskList();
        try {
            Parser.parseCommands("event marathon", testList);
        } catch (Exception e) {
            Exception m = new TobiasException("   Hey, please enter a /from and /to date/day/time!!");
            assertEquals(m.getMessage(), e.getMessage());
        }
    }

    @Test
    public void invalid_date_syntax() {
        TaskList testList = new TaskList();
        try {
            Parser.parseCommands("deadline subission /from 11-january-2023 5am", testList);
        } catch (Exception e) {
            Exception m = new TobiasException("     Hey, please enter a date & time in this format : dd-MM-yyyy HHmm !");
            assertEquals(m.getMessage(), e.getMessage());
        }
    }

}
