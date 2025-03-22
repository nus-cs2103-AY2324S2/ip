package util;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import task.Deadline;
import task.Event;
import task.ToDo;

public class CsvUtilTest {
    @Test
    public void testToTask() {
        CsvUtil c = new CsvUtil("T,false,borrow book,null,null,null");
        ToDo t = new ToDo(false, "borrow book");
        assertEquals(c.toTask(), t);
        c = new CsvUtil("D,false,return book,2/12/2019 1800,null,null");

        Deadline d = new Deadline(false, "return book",
                DateTimeUtil.parse("2/12/2019 1800"));
        assertEquals(c.toTask(), d);

        c = new CsvUtil("E,false,CS2103 Tutorial,null,2/02/2024 0800,2/02/2024 0900");
        Event e = new Event(false, "CS2103 Tutorial",
                DateTimeUtil.parse("2/02/2024 0800"), DateTimeUtil.parse("2/02/2024 0900"));
        assertEquals(c.toTask(), e);
    }

}
