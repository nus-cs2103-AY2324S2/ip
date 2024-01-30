import java.io.IOException;
import java.util.Scanner;

public class Dook {

    private static final String FILE_PATH = "src/main/dook.txt";
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
        try {
            this.tasks = this.storage.loadTaskListFromFile();
        } catch (IOException e) {
            this.ui.println("error while loading file, specific error: " + e);
        } catch (DookException e) {
            this.ui.println(e.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        boolean willExitLoop = false;
        while (!willExitLoop) {
            String input = sc.nextLine();
            this.ui.printSeparator();
            try {
                Command c = this.parser.parse(input);
                c.execute(this.tasks, this.ui, this.storage);
                willExitLoop = c.isExit();
            } catch (Exception e) {
                this.ui.printException(e);
            } finally {
                this.ui.printSeparator();
            }
        }
    }

    public static void main(String[] args) {
        Dook dook = new Dook();
        dook.run();
    }
}