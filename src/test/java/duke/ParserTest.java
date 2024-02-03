package seedu.duke;  //same package as the class being tested

import org.junit.jupiter.api.Test;

package duke;  //same package as the class being tested

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public Todo parseTodo(){
        String order = "todo reading";
        Parser parser = new Parser();
        assertEquals(new Todo("reading") , parser.parseTodo(order));
    }
}