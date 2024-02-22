package gronk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void emptyListTest() {
        Parser p = new Parser(new TaskList());
        String expected = "Nothing added yet!";
        assertEquals(p.parse("list"), expected);
    }

    @Test
    public void helpMessageTest() {
        Parser parser = new Parser(new TaskList());
        String expected = "User Guide: https://chengjunyuan.github.io/ip/\n"
                + "(I'm so sorry, you can't click the link,\n"
                + " you can also go to bit.ly/gronk_help)";
        assertEquals(parser.parse("help"), expected);
    }

    @Test
    public void todoTest1() {
        Parser p = new Parser(new TaskList());
        String expected = "Task added: run";
        assertEquals(p.parse("todo run"), expected);
    }

    @Test
    public void todoTest2() {
        Parser p = new Parser(new TaskList());
        String expected = "Task added: run";
        assertEquals(p.parse("TODO run"), expected);
    }

    @Test
    public void todoTest3() {
        Parser p = new Parser(new TaskList());
        String expected = "Task added: run";
        assertEquals(p.parse("Todo run"), expected);
    }

    @Test
    public void deadlineTest() {
        Parser p = new Parser(new TaskList());
        String expected = "Deadline added: game";
        assertEquals(p.parse("deadline game /b 17/01/2024"), expected);
    }

    @Test
    public void deadlineTest2() {
        Parser p = new Parser(new TaskList());
        String expected = "Deadline added: game";
        assertEquals(p.parse("deADLine game /b 17/01/2024"), expected);
    }

    @Test
    public void wrongDeadlineFormatTest() {
        Parser p = new Parser(new TaskList());
        String expected = "I'm sorry, I didn't recognize that command. Please try again!";
        assertEquals(p.parse("deADLine game /b 1/01/2024"), expected);
    }

    @Test
    public void wrongDeadlineFormatTest2() {
        Parser p = new Parser(new TaskList());
        String expected = "I'm sorry, I didn't recognize that command. Please try again!";
        assertEquals(p.parse("deadline game /b 1/1/24"), expected);
    }
}
