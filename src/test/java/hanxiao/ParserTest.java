package hanxiao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import hanxiao.command.Unmarking;
import org.junit.jupiter.api.Test;

import hanxiao.command.CurrentTask;
import hanxiao.command.Listing;
import hanxiao.command.Marking;
import hanxiao.exception.HanxiaoException;
import hanxiao.task.Deadline;
import hanxiao.task.Todo;


public class ParserTest {

    @Test
    void test_parse_throwHanxiaoException() throws HanxiaoException {
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.addTask(new Todo("todo task"));
        tasks.addTask(new Deadline("deadline task", LocalDate.parse("2024-11-21")));
        Parser parser = new Parser(tasks, null);
        String textOne = "list";
        String textTwo = "current";
        String textThree = "mark 1";
        String textFour = "unmark 1";

        Listing ls = new Listing(tasks);
        assertEquals(ls.reply(), parser.parse(textOne).reply());

        CurrentTask curr = new CurrentTask(tasks);
        assertEquals(curr.reply(), parser.parse(textTwo).reply());

        Marking marking = new Marking(0, tasks);
        assertEquals(marking.reply(), parser.parse(textThree).reply());

        Unmarking unmarking = new Unmarking(0, tasks);
        assertEquals(unmarking.reply(), parser.parse(textFour).reply());
    }
}
