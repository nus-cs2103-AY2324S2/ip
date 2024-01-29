import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Dook {

    private static final String FILE_PATH = "./data/dook.txt";
    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    Dook() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH);
    }

    public void run() {
        this.ui.introduce();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            this.ui.printSeparator();
            boolean willExitLoop = false;
            try {
                Command c = this.parser.parse(input);
                c.execute(this.tasks, this.ui, this.storage);
                willExitLoop = c.isExit();
            } catch (DookException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error detected, please try again :(\n" + e);
            } finally {
                this.ui.printSeparator();
            }
            if (willExitLoop) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Dook dook = new Dook();
        dook.run();
    }
}
