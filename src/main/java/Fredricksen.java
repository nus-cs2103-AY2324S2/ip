import java.io.IOException;
import java.util.NoSuchElementException;

import commands.Command;
import storages.Storage;
import tasks.TaskList;
import ui.Ui;

public class Fredricksen {
    private Ui ui = new Ui();
    private Storage store = new Storage("data/Fredricksen.txt");
    private TaskList list;

    public Fredricksen() {
        try {
            this.list = new TaskList(store.loadFile());
        } catch (IOException error) {
            ui.showError(error);
            this.list = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isLoop = true;
        // while loop
        while (isLoop) {
            System.out.println("");
            String task = "";
            try {
                task = ui.readCommand();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                break;
            }
            String[] splitTask = task.split(" ");
            if (splitTask[0].equals("bye")) {
                isLoop = false;
            } else {
                Command command = new Command(task);
                command.execute(task, list, ui, store);
            }
        }
        store.updateFile(list);
        ui.closeScanner();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    public void getResponse() {
        this.run();
    }

    public static void main(String[] args) {
        Fredricksen fred = new Fredricksen();
        fred.run();
    }
}
