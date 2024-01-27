package parser;

import exceptions.InvalidCommandException;
import exceptions.InvalidDateException;
import exceptions.InvalidSlashParameterException;
import exceptions.LuluException;
import org.junit.jupiter.api.Test;
import command.Command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void test1() throws LuluException {
        String input = "event event1 /from 2022-03-04 /to 2024-05-06";
        Parser parser = new Parser();
        Command command = parser.parse(input);

        assertEquals("event,event1,2022-03-04,2024-05-06,false", command.getTestData());
    }

    @Test
    public void test2() throws LuluException {
        String input = "event event1 /from 2022-03-04 /to 2022-03-04";
        Parser parser = new Parser();
        Command command = parser.parse(input);
        assertEquals("event,event1,2022-03-04,2022-03-04,false", command.getTestData());
    }

    @Test
    public void test3() throws LuluException {
        String input = "find deadline 2023-01-02";
        Parser parser = new Parser();
        Command command = parser.parse(input);
        assertEquals("deadline", command.getTestData());
    }

    @Test
    public void test4() {
        String input = "event event1 /from 2022-03-04 /to 2021-03-04";
        Parser parser = new Parser();
        assertThrows(InvalidDateException.class, () -> parser.parse(input));
    }

    @Test
    public void test5() {
        String input = "event";
        Parser parser = new Parser();
        assertThrows(InvalidCommandException.class, () -> parser.parse(input));
    }

    @Test
    public void test6() {
        String input = "event event1 /from 2022-03-04 / 2023-03-04";
        Parser parser = new Parser();
        assertThrows(InvalidSlashParameterException.class, () -> parser.parse(input));
    }
}
