import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

public class Lindi {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private final String NAME = "Lindi";  // Log It N Do It -> LINDI

    public Lindi(String dataDir, String dataFileName) {
        this.storage = new Storage(dataDir, dataFileName);
        this.ui = new Ui(this.NAME);
        try {
            this.tasks = this.storage.loadFromFile();
        } catch (StorageLoadException e) {
            this.ui.displayError(e);
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.ui.greeting();

        boolean toExit = false;
        while (!toExit) {
            String userInput = this.ui.getUserInput();
            Command c = Parser.parse(userInput);
            c.execute(this.tasks, this.storage);
            this.ui.displayCommand(c);
            toExit = c.isExit();
        }
    }

//    private static void chatLoop() {
//        Scanner scanner = new Scanner(System.in);
//        String userInput;
//        while (true) { // This will not be an infinite loop, because goodByeAndExit() terminates the program when called
//            userInput = scanner.nextLine();
//            printSeparator();
//            parseInputAndExecute(userInput);
//            printSeparator();
//        }
//    }
    public static void main(String[] args) throws IOException {
        new Lindi("./.data", "LindiData.txt").run();
//        greeting();
//        loadFromFile();
//        chatLoop();
    }
}
