//package seedu.duke; //same package as the class being tested
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.io.PrintStream;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import duke.core.Chatbot;
//
//public class DukeTest {
//
//    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private final PrintStream originalOut = System.out;
//
//    @BeforeEach
//    public void setUpStreams() {
//        System.setOut(new PrintStream(outContent));
//    }
//
//    @AfterEach
//    public void restoreStreams() {
//        System.setOut(originalOut);
//    }
//
//    @Test
//    void testChatSequence() {
//        String inputSequence = String.join(
//                System.lineSeparator(),
//                "todo borrow book",
//                "deadline return book /by 2/12/2019 1800",
//                "event project meeting /from 2020-01-01 /to 2020-01-02",
//                "list",
//                "mark 2",
//                "list",
//                "bye"
//        );
//
//        InputStream inContent = new ByteArrayInputStream(inputSequence.getBytes());
//        Chatbot tribara = new Chatbot("Tribara");
//        tribara.getResponse(inContent, System.out);
//
//        String output = outContent.toString();
//
//        // Assertion for greeting
//        assertTrue(output.contains("-----------------------------------------------------------------------\n" +
//                "Heyoo I'm Tribara.\n" +
//                "What can I do for you?\n" +
//                "-----------------------------------------------------------------------\n" +
//                "-----------------------------------------------------------------------\n" +
//                "Gotcha. I've added this task: \n" +
//                "    [T][ ] borrow book\n" +
//                "Now you have 1 tasks in the list.\n" +
//                "-----------------------------------------------------------------------\n" +
//                "-----------------------------------------------------------------------\n" +
//                "Gotcha. I've added this task: \n" +
//                "    [D][ ] return book (by: Dec 02 2019, 18:00)\n" +
//                "Now you have 2 tasks in the list.\n" +
//                "-----------------------------------------------------------------------\n" +
//                "-----------------------------------------------------------------------\n" +
//                "Gotcha. I've added this task: \n" +
//                "    [E][ ] project meeting (from: Jan 01 2020, 00:00 to: Jan 02 2020, 00:00)\n" +
//                "Now you have 3 tasks in the list.\n" +
//                "-----------------------------------------------------------------------\n" +
//                "-----------------------------------------------------------------------\n" +
//                "Here are the tasks in your list: \n" +
//                "1. [T][ ] borrow book\n" +
//                "2. [D][ ] return book (by: Dec 02 2019, 18:00)\n" +
//                "3. [E][ ] project meeting (from: Jan 01 2020, 00:00 to: Jan 02 2020, 00:00)\n" +
//                "-----------------------------------------------------------------------\n" +
//                "-----------------------------------------------------------------------\n" +
//                "Good job on finishing this task: \n" +
//                "    [D][X] return book (by: Dec 02 2019, 18:00)\n" +
//                "-----------------------------------------------------------------------\n" +
//                "-----------------------------------------------------------------------\n" +
//                "Here are the tasks in your list: \n" +
//                "1. [T][ ] borrow book\n" +
//                "2. [D][X] return book (by: Dec 02 2019, 18:00)\n" +
//                "3. [E][ ] project meeting (from: Jan 01 2020, 00:00 to: Jan 02 2020, 00:00)\n" +
//                "-----------------------------------------------------------------------\n" +
//                "-----------------------------------------------------------------------\n" +
//                "Bye lol see you again!\n" +
//                "-----------------------------------------------------------------------\n"));
//    }
//}
