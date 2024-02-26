package aegis;

import java.io.File;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * AegisTest contains methods to test tagTask() in Aegis class.
 *
 * Implemented for Aegis GUI.
 */
public class AegisTest {
    private final String DIRECTORY_PATH = "./src/test/data";
    private final String FILE_PATH = "./src/test/data/aegisTest_valid.txt";

    /**
     * Tests tagTask() with valid input.
     */
    @Test
    public void tagTaskValidInputTest() {
        // File is deleted and a new file with untagged
        // newly created tasks is created to prevent unintentional
        // tag stacking which may mess up checking.
        File fileGrab = new File(FILE_PATH);
        fileGrab.delete();

        Aegis aegis = new Aegis(DIRECTORY_PATH, FILE_PATH);
        aegis.getResponse("todo Dummy Task 1");
        aegis.getResponse("todo Dummy Task 2");
        aegis.getResponse("todo Dummy Task 3");

        String check1 = aegis.getResponse("tag 1 #hello").get(0);
        String check2 = aegis.getResponse("tag 2 #world").get(0);
        String check3 = aegis.getResponse("tag 3 #java").get(0);

        assertEquals("Confirmed. The following task has been tagged with #hello:\n[T][ ] Dummy Task 1 #hello", check1);
        assertEquals("Confirmed. The following task has been tagged with #world:\n[T][ ] Dummy Task 2 #world", check2);
        assertEquals("Confirmed. The following task has been tagged with #java:\n[T][ ] Dummy Task 3 #java", check3);
    }

    /**
     * Tests tagTask() with missing arguments in input.
     */
    @Test
    public void tagTaskMissingArgumentsTest() {
        // File is deleted and a new file with untagged
        // newly created tasks is created to prevent unintentional
        // tag stacking which may mess up checking.
        File fileGrab = new File(FILE_PATH);
        fileGrab.delete();

        Aegis aegis = new Aegis(DIRECTORY_PATH, FILE_PATH);
        aegis.getResponse("todo Dummy Task 1");

        String check1 = aegis.getResponse("tag").get(0);
        assertEquals("Invalid command given for tagging task.\nPlease provide command in the following format:\ntag <Task Index> #<Tag>", check1);

        String check2 = aegis.getResponse("tag 1").get(0);
        assertEquals("Invalid command given for tagging task.\nPlease provide command in the following format:\ntag <Task Index> #<Tag>", check2);
    }

    /**
     * Tests tagTask() with invalid task index.
     */
    @Test
    public void tagTaskInvalidIndexTest() {
        // File is deleted and a new file with untagged
        // newly created tasks is created to prevent unintentional
        // tag stacking which may mess up checking.
        File fileGrab = new File(FILE_PATH);
        fileGrab.delete();

        Aegis aegis = new Aegis(DIRECTORY_PATH, FILE_PATH);
        aegis.getResponse("todo Dummy Task 1");

        String check1 = aegis.getResponse("tag -1 #tag").get(0);
        assertEquals("Invalid task index provided.\nPlease provide a valid task index.\n", check1);

        String check2 = aegis.getResponse("tag 9999 #tag").get(0);
        assertEquals("Invalid task index provided.\nPlease provide a valid task index.\n", check2);
    }

    /**
     * Tests tagTask() with invalid tag format.
     */
    @Test
    public void tagTaskInvalidTagFormatTest() {
        // File is deleted and a new file with untagged
        // newly created tasks is created to prevent unintentional
        // tag stacking which may mess up checking.
        File fileGrab = new File(FILE_PATH);
        fileGrab.delete();

        Aegis aegis = new Aegis(DIRECTORY_PATH, FILE_PATH);
        aegis.getResponse("todo Dummy Task 1");

        String check1 = aegis.getResponse("tag 1 tag").get(0);
        assertEquals("Invalid format for tag.\nPlease provide a tag in the following format:\n#<tag>", check1);

        String check2 = aegis.getResponse("tag 1 hello#").get(0);
        assertEquals("Invalid format for tag.\nPlease provide a tag in the following format:\n#<tag>", check2);

        String check3 = aegis.getResponse("tag 1 $tag").get(0);
        assertEquals("Invalid format for tag.\nPlease provide a tag in the following format:\n#<tag>", check3);
    }
}
