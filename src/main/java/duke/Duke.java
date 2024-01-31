package duke;

import java.util.Scanner;
import java.io.File;

/**
 * Main class of the program.
 *
 * @author Tania Tan Shu Qi
 */
public class Duke {

    /**
     * Calls to Ui ui to start and end the program.
     * Calls to TaskList tasklist to write current tasks to File f.
     * @param args Command-line arguments passed to the program
     */
    public static void main(String[] args) {

        File f = new File("data/EUEU.txt");
        Scanner user = new Scanner(System.in);
        Storage storage = new Storage(f);
        TaskList tasklist = new TaskList(storage);

        Ui ui = new Ui(user, tasklist);
        System.out.println(ui.showWelcome());
        ui.readCommand();
        tasklist.write();
        System.out.println(ui.exit());

    }
}