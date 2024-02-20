package goldbot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class SubcommandParserTest {
    @Test
    public void parseSubcommandsTest() {
        Pair<String, List<Pair<String, String>>> data = SubcommandParser.parseSubcommands("command", "|");
        assertEquals(data.getFirst(), "command");
        assertEquals(data.getSecond().size(), 0);

        Pair<String, List<Pair<String, String>>> dataOne = SubcommandParser.parseSubcommands("command /subcmd 1", "/");
        assertEquals("command", dataOne.getFirst());
        assertEquals("/subcmd", dataOne.getSecond().get(0).getFirst());
        assertEquals("1", dataOne.getSecond().get(0).getSecond());


        Pair<String, List<Pair<String, String>>> dataTwo = SubcommandParser.parseSubcommands("command /subcmd", "/");
        assertEquals("command", dataTwo.getFirst());
        assertEquals("/subcmd", dataTwo.getSecond().get(0).getFirst());
        assertEquals("", dataTwo.getSecond().get(0).getSecond());

        Pair<String, List<Pair<String, String>>> dataThree =
            SubcommandParser.parseSubcommands("command /subcmd1 arg11 arg12 /subcmd2 arg21 arg22", "/");
        assertEquals("command", dataThree.getFirst());
        assertEquals("/subcmd1", dataThree.getSecond().get(0).getFirst());
        assertEquals("arg11 arg12", dataThree.getSecond().get(0).getSecond());
        assertEquals("/subcmd2", dataThree.getSecond().get(1).getFirst());
        assertEquals("arg21 arg22", dataThree.getSecond().get(1).getSecond());
    }
}
