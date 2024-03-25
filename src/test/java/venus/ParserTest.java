package venus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void findDeadlineContentTest() throws DukeException {
        String[] test = {"bye bye", "21380-100-01"};
        assertEquals(test[0],
                Parser.findDeadlineContent("deadline bye bye /by 21380-100-01")[0]);
        assertEquals(test[1],
                Parser.findDeadlineContent("deadline bye bye /by 21380-100-01")[1]);
    }

    @Test
    public void findDeadlineContentTest2() {
        String[] test = {"bye bye", "21380-100-01"};
        DukeException dukeException =
                assertThrows(DukeException.class, () -> Parser.findDeadlineContent("deadline 2 /by dl /by "));
        assertEquals(dukeException.getMessage(),
                "Incorrect, choose a specific deadline only please");
    }

    @Test
    public void findEventContentTest() throws DukeException {
        String[] expected = {"event", "from bye bye", "to 21380"};
        String[] actual = Parser.findEventParts("event event /from bye bye/to 21380");
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
    }

    @Test
    public void findEventContentTest2() throws DukeException {
        String[] expected = {"event", "from2019-10-10", "to   21380"};
        String[] actual = Parser.findEventParts("event event /from2019-10-10/to   21380 ");
        assertEquals(expected[0], actual[0]);
        assertEquals(expected[1], actual[1]);
        assertEquals(expected[2], actual[2]);
    }

    @Test
    public void findfindType() {
        DukeException e = assertThrows(DukeException.class, () -> Parser.findDeadlineContent("ee something"));
    }

    @Test
    public void findfindType2() {
        assertEquals(TaskList.Types.EVENT, Parser.findType("event deadline deadline"));
    }

    @Test
    public void findfindType3() {
        assertEquals(TaskList.Types.EVENT, Parser.findType("event deadlinetodo"));
    }
}
