package dave;

import org.junit.jupiter.api.Test;

import dave.exceptions.ChatbotException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_deleteCommand_exceptionThrown() {
        try {
            Parser.parse("delete 1");
        } catch (ChatbotException exc) {
            assertEquals("__________________________________________________\r\n" + //
                        "\r\n" + //
                        "Dave could not find the specified task.\r\n" + //
                        "Note that there are 0 task(s) currently.\r\n" + //
                        "____________________________________________________________\r\n",
                        exc.getMessage());
        }
    }

    @Test
    public void parse_newDeadlineCommand_exceptionThrown() {
        try {
            Parser.parse("deadline");
        } catch (ChatbotException exc) {
            assertEquals("____________________________________________________________" +
                        "\nDave cannot record a DEADLINE task without a name/deadline." +
                        "\nPlease help Dave by entering your DEADLINE task as follows:" +
                        "\n\ndeadline <name> /by dd-mm-yyyy hhmm" +
                        "\n____________________________________________________________",
                        exc.getMessage());
        }
    }
}
