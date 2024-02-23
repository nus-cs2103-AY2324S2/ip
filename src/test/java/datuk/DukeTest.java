package datuk;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import datuk.task.Deadline;
import datuk.task.Task;
import datuk.task.Todo;

public class DukeTest {

    @Test
    public void parserMarkTest() {
        UI ui = new UI();
        Parser parser = new Parser();

        try {
            parser.parseMark("mark 2 3");
        } catch (DukeException de) {
            assertEquals("Incorrect number of params for mark/unmark", de.getMessage());
        }

        try {
            parser.parseMark("mark");
        } catch (DukeException de) {
            assertEquals("Incorrect number of params for mark/unmark", de.getMessage());
        }

        try {
            String[] test = parser.parseMark("mark 2");
            assertEquals(test[0], "mark");
            assertEquals(test[1], "2");
        } catch (DukeException de) {
            ui.showError(de);
        }
    }

    @Test
    public void deleteTest() {
        UI ui = new UI();
        ArrayList<Task> testList = new ArrayList<>();
        testList.add(new Todo("test"));
        testList.add(new Deadline("test", LocalDate.parse("2020-10-10")));
        TaskList tasks = new TaskList(testList);

        String[] test1 = {"delete", "3"};
        String[] test2 = {"delete", "2"};

        try {
            tasks.deleteItem(test1);
        } catch (DukeException de) {
            assertEquals("Index does not exist!", de.getMessage());
        }

        try {
            tasks.deleteItem(test2);
        } catch (DukeException de) {
            assertEquals(1, tasks.get().size());
        }
    }
}
