package ally.utils;

import ally.task.Todo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FinderTest {

    TaskList lst;
    Finder finder;

    @BeforeEach
    void setUp() {
        lst = new TaskList();
        lst.addToList(new Todo("eat"));
        lst.addToList(new Todo("sleep"));
        finder = new Finder(lst);
    }

    @Test
    void testFindPositive() {
        String result = this.finder.find("sleep");
        assertEquals(result, "2: [T][ ] sleep\n" +
                "All results shown.");
    }

    @Test
    void testFindNegative() {
        String result = this.finder.find("cs3230 homework");
        assertEquals(result, "No results Found.");
    }
}
