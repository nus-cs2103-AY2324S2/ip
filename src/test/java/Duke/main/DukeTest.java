package duke.main;

import org.junit.jupiter.api.Test;

public class DukeTest {
    @Test
    public void testRun() {
        String userInput = "Namehu";
        Duke duke = new Duke();
        System.out.println(duke.run(userInput));
    }
}
