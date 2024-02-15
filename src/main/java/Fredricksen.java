import java.io.IOException;
import java.util.NoSuchElementException;

import formatter.ParseInput;
import commands.ByeCommand;
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
        System.out.println("");
        ui.showWelcome();
        while (true) {
            String task = "";
            try {
                task = ui.readCommand();
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
                break;
            }
            ParseInput parseInput = new ParseInput();
            Command executableCommand = parseInput.getCommand(task, this.list);
            String response = executableCommand.execute();
            System.out.println(response);
            if (executableCommand instanceof ByeCommand) {
                break;
            }
        }
        store.updateFile(list);
        ui.closeScanner();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */

    public String getResponse(String input) {
        ParseInput parseInput = new ParseInput();
        Command executableCommand = parseInput.getCommand(input, this.list);
        return executableCommand.execute();
    }

    public static void main(String[] args) {
        Fredricksen fred = new Fredricksen();
        fred.run();
    }
}
