package bentley;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void deadlineToStringFormat() throws DukeException {
        Storage storage = new Storage("src/test/java/data/duke.txt");
        TaskList taskList = new TaskList(new ArrayList<>());
        Parser.addDeadlineTask("deadline return books /by 2025-09-09",taskList, storage );

        assertEquals("[  ] (D) return books | Sep 09 2025", taskList.getTasks().get(0).toString());
    }

}