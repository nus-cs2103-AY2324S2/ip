import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {
    public static void main(String[] args) {

        File f = new File("data/EUEU.txt");
        Scanner user = new Scanner(System.in);
        ArrayList<Task> tasklist = new ArrayList<Task>();

        Ui ui = new Ui(user, tasklist, f);
        ui.showWelcome();
        ui.readCommand();
        for (int i = 0; i < tasklist.size(); i++) {
            tasklist.get(i).writeToFile(f);
        }

    }
}