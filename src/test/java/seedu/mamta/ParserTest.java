/*
package seedu.mamta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testTransformTextTodo() {
        String input = "todo Task 1";
        String expectedOutput = "------------------------------------------\nGot it. I've added this task: \nT| |Task 1 \nNow you have 4 tasks in the list\n------------------------------------------";
        String actualOutput = Parser.transformText(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testTransformTextDeadline() {
        String input = "deadline Complete assignment /by 2024-02-06";
        String expectedOutput = "------------------------------------------\nGot it. I've added this task: \nD| |Complete assignment|2024-02-06\nNow you have 2 tasks in the list\n------------------------------------------";
        String actualOutput = Parser.transformText(input);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testTransformTextEvent() {
        String input = "event Birthday party /from 2024-02-06 /to 2024-02-07";
        String expectedOutput = "------------------------------------------\nGot it. I've added this task: \nE| |Birthday party|2024-02-06|2024-02-07\nNow you have 6 tasks in the list\n------------------------------------------";
        String actualOutput = Parser.transformText(input);
        assertEquals(expectedOutput, actualOutput);
    }

    // Helper method to execute the transformText method and return its output

}
*/