package duke;

import java.util.Scanner;
import java.io.File;

public class Duke {
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