import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tiny {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser = new Parser();

    public Tiny(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TinyException e) {
            ui.showLoadingError();
        }
    }

    public void run() throws IOException {
        ui.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = br.readLine();
                printContent(parser.parse(input, tasks));
                storage.save(tasks.toSave());
                isExit = parser.isExit();
            } catch (TinyException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Tiny("../../../data/tasks.txt").run();
    }

    // Printing Methods
    public static void tabPrint(String input) {
        System.out.println("   " + input);
    }

    public static void printLine() {
        tabPrint("____________________________________________________________\n");
    }

    public static void printContent(String input) {
        printLine();
        tabPrint(input);
        printLine();
    }
}
