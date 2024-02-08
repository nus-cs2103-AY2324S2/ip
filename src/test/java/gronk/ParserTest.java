package gronk;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void listTest() {
        Storage storage = new Storage("testTasks.txt");
        storage.loadFromFile();
        Parser parser = new Parser(storage.returnTasks());
        String expected = "\t1. [T] [ ] gaming\n"
                + "\t2. [D] [ ] swim (by: Jan 1 2025)\n"
                + "\t3. [T] [X] game\n"
                + "\t4. [T] [X] fight";
        assertEquals(parser.parse("list"), expected);
    }

    @Test
    public void addTodoTest() {
        Storage storage = new Storage("testTasks.txt");
        storage.loadFromFile();
        Parser parser = new Parser(storage.returnTasks());
        String expected = "\tTask added: read";
        assertEquals(parser.parse("todo read"), expected);
    }
}
