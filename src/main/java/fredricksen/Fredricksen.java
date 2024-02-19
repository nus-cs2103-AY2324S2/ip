package fredricksen;

import java.io.IOException;
import java.util.NoSuchElementException;

import fredricksen.commands.ByeCommand;
import fredricksen.commands.Command;
import fredricksen.formatter.ParseInput;
import fredricksen.storages.Storage;
import fredricksen.tasks.TaskList;
import fredricksen.ui.Ui;

/**
 * The fredricksen.Fredricksen class represents a chatbot that manages tasks.
 * It is the main class that contains the methods to run the chatbot.
 * It also serves as one of the entry points of the chatbot program.
 */
public class Fredricksen {
    private Ui ui;
    private Storage store;
    private TaskList list;

    /**
     * Creates a fredricksen.Fredricksen instance that represents the chatbot without any parameters.
     */
    public Fredricksen() {
        this.ui = new Ui();
        this.store = new Storage("data/Fredricksen.txt");
        try {
            this.store.createFileInData();
            this.list = new TaskList(store.loadFile());
        } catch (IOException error) {
            this.ui.showError(error);
            this.list = new TaskList();
        }
    }

    /**
     * Runs the fredricksen.Fredricksen chatbot. It reads and executes commands
     * based on the user until the user types the bye command or force close the program.
     */
    public void run() {
        System.out.println("");
        System.out.println(ui.showWelcome());
        while (true) {
            String task = "";
            try {
                task = ui.readCommand();
                assert task.equals("") : "Valid command to process";
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
        store.updateFile(list);
        return executableCommand.execute();
    }

    public static void main(String[] args) {
        Fredricksen fred = new Fredricksen();
        fred.run();
    }
}
