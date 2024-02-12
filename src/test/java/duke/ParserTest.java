package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.command.CurrentTask;
import duke.command.List;
import duke.command.Mark;
import duke.command.Unmark;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Todo;


public class ParserTest {

    @Test
    void test_parse_throwDukeException() throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(new Todo("todo task"));
        tasks.addTask(new Deadline("deadline task", LocalDate.parse("2024-11-21")));
        Parser parser = new Parser(tasks, null);
        String textOne = "list";
        String textTwo = "current";
        String textThree = "mark 1";
        String textFour = "unmark 1";

        List ls = new List(tasks);
        assertEquals(ls.reply(), parser.parse(textOne).reply());

        CurrentTask curr = new CurrentTask(tasks);
        assertEquals(curr.reply(), parser.parse(textTwo).reply());

        Mark mark = new Mark(0, tasks);
        assertEquals(mark.reply(), parser.parse(textThree).reply());

        Unmark unmark = new Unmark(0, tasks);
        assertEquals(unmark.reply(), parser.parse(textFour).reply());
    }
}
