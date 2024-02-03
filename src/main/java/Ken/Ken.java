
import java.util.Scanner;

public class Ken {
    private static final String FILE_PATH = "./data/ken.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ken(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTask());
        } catch (KenException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.welcomeMessage();
        Parser parser = new Parser(tasks, storage);
        try {
            parser.parseUserCommands();
        } catch (KenException e) {
            e.printStackTrace();
        } finally {
            if (parser.hasSaidBye()) {
                ui.byeMessage();
            }
        }
    }
    public static void main(String[] args) {
        new Ken(FILE_PATH).run();
    }
}
