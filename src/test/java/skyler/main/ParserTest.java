package skyler.main;

import org.junit.jupiter.api.Test;
import skyler.exception.SkylerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void processUserInput_eventCommand_addsEventTask() throws SkylerException {
        String userInput = "event Team meeting /from 2022-01-20 /to 2022-01-21";
        Parser.processUserInput(userInput);
        assertEquals("[E][ ] Team meeting (from: Jan 20 2022 to: Jan 21 2022)", Storage.getTasks().get(0).toString());
    }

    @Test
    public void processUserInput_invalidCommand_throwsSkylerException() {
        String userInput = "invalidCommand";
        assertThrows(SkylerException.class, () -> Parser.processUserInput(userInput));
    }

}
