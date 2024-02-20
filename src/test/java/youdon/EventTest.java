package youdon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    // no save file, add first task
    public void testValidEvent() {
        // delete save file
        File fileToDelete = new File("./data/save.txt");
        fileToDelete.delete();

        // set input in input stream
        ByteArrayInputStream inputStream = new ByteArrayInputStream(
                "event project meeting /from 5/6/2020 1900 /to 7/7/2021 1900".getBytes());
        System.setIn(inputStream);

        // capture output in output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // run main()
        Youdon.main(null);

        // retrieve output and replace all system line separators with \n
        String actualOutput = outputStream.toString().replaceAll(System.lineSeparator(), "\n");

        // reset input and output back to System
        System.setIn(System.in);
        System.setOut(System.out);

        // expected output
        String expectedOutput = "Save File created successfully!\n"
                + "Save File is empty :(\n"
                + "----------------------------------------------------------------\n"
                + "Hello! I'm Youdon!\n"
                + "What can I do for you?\n"
                + "\n"
                + "----------------------------------------------------------------\n"
                + "----------------------------------------------------------------\n"
                + "Alright! Task added:\n  "
                + "[E][ ] project meeting (from: Jun 05 2020 19:00 to: Jul 07 2021 19:00)\n"
                + "You now have 1 task(s) in the list.\n"
                + "----------------------------------------------------------------\n"
                + "Tasklist saved!";

        // assert actual and expected output
        assertEquals(expectedOutput.trim(), actualOutput.trim());
    }
}
