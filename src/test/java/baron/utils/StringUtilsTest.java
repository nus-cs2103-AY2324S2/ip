package baron.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StringUtilsTest {
    @Test
    public void getIndexOfWithOffset_toFindExists_returnsIndex() {
        String input = "a b /by c";
        int actual = StringUtils.getIndexOf(input, "/by", -1);
        int expected = input.indexOf("/by") - 1;
        assertEquals(actual, expected);
    }

    @Test
    public void getIndexOfWithOffset_toFindDoesNotExist_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            String input = "a b /by c";
            int actual = StringUtils.getIndexOf(input, "/bye", 0);
        });
    }

    @Test
    public void getIndexOfWithOffset_indexOutOfBounds_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            String input = "a b /by c";
            StringUtils.getIndexOf(input, "a", -1);
            StringUtils.getIndexOf(input, "c", 1);
        });
    }

    @Test()
    public void splitDataString_multipleSegments_segmentsReturned() {
        String input = " 0| mary| had |a |little|    lamb    ";
        String[] actual = StringUtils.splitDataString(input);
        String[] expected = new String[]{"0", "mary", "had", "a", "little", "lamb"};
        assertArrayEquals(actual, expected);
    }

    @Test()
    public void splitDataString_noSegments_inputReturned() {
        String input = " this has no segments";
        String[] actual = StringUtils.splitDataString(input);
        String[] expected = new String[]{"this has no segments"};
        assertArrayEquals(actual, expected);
    }

    @Test()
    public void getValueOfCommand_valueExists_commandValueReturned() {
        String input = "deadline homework submission /by tonight";
        String actual = StringUtils.getValueOfCommand(input, "/by", null);
        String expected = "tonight";
        assertEquals(actual, expected);
    }

    @Test()
    public void getValueOfCommandBetweenWords_valueExists_commandValueReturned() {
        String input = "deadline   homework submission   /by tonight";
        String actual = StringUtils.getValueOfCommand(input, "deadline", "/by");
        String expected = "homework submission";
        assertEquals(actual, expected);
    }

    @Test()
    public void getValueOfCommandBetweenWords_commandDoesNotExist_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            String input = "deadline   homework submission   /by tonight";
            String actual = StringUtils.getValueOfCommand(input, "todo", null);
        });
    }

    @Test()
    public void getValueOfCommandBetweenWords_stopWordDoesNotExist_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> {
            String input = "deadline   homework submission   /by tonight";
            String actual = StringUtils.getValueOfCommand(input, "deadline", "/from");
        });
    }
}
