import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.io.File;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.loadTasks());
        run();

    }

    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            String userInput = ui.readCommand();
            try {
                Command c = Parser.parse(userInput);
                c.execute(taskList, ui, storage, userInput);
            } catch (DukeException e) {
            System.out.println(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
            System.out.println("Quit yappin, that task does not exist");
        }


        }
        ui.exit();
    }
    public static void main(String[] args) {
        new Duke("./data/taskyapper.txt");
    }

}
