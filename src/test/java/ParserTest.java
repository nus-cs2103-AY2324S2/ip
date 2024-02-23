package simpli.parser; //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import simpli.exceptions.CommandException;

public class ParserTest {
    @Test
    public void parseCommandSuccessTest() throws CommandException {
        Parser parser = new Parser();

        assertArrayEquals(new String[]{"list", "0", "", "", ""},
                parser.parseCommand("list"));
        assertArrayEquals(new String[]{"mark", "0", "1", "", ""},
                parser.parseCommand("mark 1"));
        assertArrayEquals(new String[]{"unmark", "0", "2", "", ""},
                parser.parseCommand("unmark 2"));
        assertArrayEquals(new String[]{"delete", "0", "3", "", ""},
                parser.parseCommand("delete 3"));
        assertArrayEquals(new String[]{"todo", "0", "passtest1", "", ""},
                parser.parseCommand("todo passtest1"));
        assertArrayEquals(new String[]{"deadline", "0", "passtest2", "30/4/2022 1400", ""},
                parser.parseCommand("deadline passtest2 /by 30/4/2022 1400"));
        assertArrayEquals(new String[]{"event", "0", "passtest3", "1/3/2022 0000", "13/3/2022 0000"},
                parser.parseCommand("event passtest3 /from 1/3/2022 0000 /to 13/3/2022 0000"));
    }

    @Test
    public void parseCommandFailureTest() {
        Parser parser = new Parser();

        try {
            parser.parseCommand("yaba");
        } catch (CommandException e) {
            assertNotNull(e);
        }
    }

    @Test
    public void parseCsvSuccessTest() {
        Parser parser = new Parser();

        assertArrayEquals(new String[]{"hello", "there", "here", "is", ""},
                parser.parseCsv("hello,there,here,is"));
        assertArrayEquals(new String[]{"todo", "0", "homework", "", ""},
                parser.parseCsv("todo,0,homework"));
    }

    @Test
    public void parseCsvFailureTest() {
        Parser parser = new Parser();

        try {
            parser.parseCsv("hello,there,is,no,such,csv");
        } catch (IndexOutOfBoundsException e) {
            assertNotNull(e);
        }
    }
}
