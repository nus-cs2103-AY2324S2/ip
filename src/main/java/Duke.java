import java.util.Scanner;
import java.io.File;

public class Duke {
    public static void main(String[] args) {

        File f = new File("data/EUEU.txt");
        Scanner user = new Scanner(System.in);
        Storage storage = new Storage(f);
        TaskList tasklist = new TaskList(storage);

        Ui ui = new Ui(user, tasklist);
        ui.showWelcome();
        ui.readCommand();
        tasklist.write();

    }
}