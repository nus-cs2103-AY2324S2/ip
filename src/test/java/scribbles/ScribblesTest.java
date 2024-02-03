package scribbles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScribblesTest {

    @Test
    public void parseInputTest() {
        Scribbles scribbles = new Scribbles("src/main/java/testData.txt");

        // test case 1
        assertEquals(false, scribbles.isParsedInput("list"));

        // test case 2
        assertEquals(false, scribbles.isParsedInput("todo homework1"));

        // test case 3
        assertEquals(false, scribbles.isParsedInput("deadline homework2 /by 01/01/2024 2359"));

        // test case 4
        assertEquals(false, scribbles.isParsedInput("event lesson /from 01/01/2024 1100 /to 01/01/2024 1200"));

        // test case 5
        assertEquals(false, scribbles.isParsedInput("mark 1"));

        // test case 6
        assertEquals(false, scribbles.isParsedInput("unmark 1"));

        // test case 7
        assertEquals(false, scribbles.isParsedInput("delete 3"));

        // test case 8
        assertEquals(false, scribbles.isParsedInput("delete 2"));

        // test case 9
        assertEquals(false, scribbles.isParsedInput("delete 1"));

        // test case 10
        assertEquals(false, scribbles.isParsedInput("gibberish text"));

        // test case 11
        assertEquals(true, scribbles.isParsedInput("bye"));


    }

}
