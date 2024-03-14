package bentley;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {

    @Test
    public void TodoToStringFormat() throws DukeException {
        Storage storage = new Storage("src/test/java/data/duke.txt");
        TaskList taskList = new TaskList(new ArrayList<>());
        Parser.addTodoTask("Todo Homework",taskList, storage );

        assertEquals("[  ] (T) Homework", taskList.getTasks().get(0).toString());
    }

}
