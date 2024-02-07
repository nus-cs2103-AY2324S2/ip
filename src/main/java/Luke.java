import java.util.ArrayList;
import java.util.Scanner;

public class Luke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private static final String FILE_PATH = "./src/main/data/luke.txt";

    public Luke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readTask());

        } catch (FileException e) {
            ui.getErrorMessage(e.getMessage());
            tasks = new TaskList();
        }

    }

    public void run() {
        // Greetings
        ui.welcome();

        boolean isExit = false;
        Parser parser = new Parser("");

        // Conditions
        while (!isExit) {
            String inputString = ui.readCommand();
            parser.setInput(inputString);
            parser.parse(tasks, ui, storage);
            isExit = parser.isExit();

        }

        // Bye and exits
        ui.goodbye();
    }

    public static void main(String[] args) throws LukeException {
        new Luke(FILE_PATH).run();
        ArrayList<Task> list = new ArrayList<>();
        Ui ui = new Ui();
        Storage storage = new Storage(FILE_PATH);


    }
}
