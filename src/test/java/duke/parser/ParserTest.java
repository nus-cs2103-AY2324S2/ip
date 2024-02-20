package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void parse_success() {
        String sampleInput = "todo hw";
        Parser parser = new Parser();
        parser.feed(sampleInput);
        try {
            Token output = parser.parse();
            assertEquals(output.getCmd(), Command.TODO);
            assertEquals(output.getSelectedItem(), 0);
            assertEquals(output.getTask().getOriginalCommand(), sampleInput);
            assertEquals(output.getTask().toString(), "[T][ ] hw ");
        } catch (Exception e) {
            System.out.println("fix your tests!!!!");
        }

    }

}
