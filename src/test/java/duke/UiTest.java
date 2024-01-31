package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;
import java.io.File;
import duke.Storage;
public class UiTest {

    @Test
    public void welcomeMessage() {
        File f = new File("");
        Storage s = new Storage(f);
        TaskList tasklist = new TaskList(s);
        Scanner scanner = new Scanner(System.in);
        assertEquals("Hi babyyy! It's your EUEU!! \n" + "What are you doing today??",
                new Ui(scanner, tasklist).showWelcome());
    }
    @Test
    public void exitMessage() {
        File f = new File("");
        Storage s = new Storage(f);
        TaskList tasklist = new TaskList(s);
        Scanner scanner = new Scanner(System.in);
        assertEquals("byeee love uu ttyl ok!",
                new Ui(scanner, tasklist).exit());
    }


}
