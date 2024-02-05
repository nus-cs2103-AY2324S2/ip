import riz.data.*;
import riz.io.*;

import java.util.Scanner;

/**
 * This is the main class of the RizBot.
 * This class mainly consists of the interactions between the classes TaskList, Storage.
 * The filepath in which data is saved is also determined here as it is passed into the Storage class.
 * The input given by the user, the user's TaskList and Saved data are passed into a Parser which processes the input.
 */
public class Riz {
    private TaskList taskList;
    private Storage storage;

    /**
     * The constructor initialises a Storage object with the filepath given
     * and loads the TaskList with the tasks from memory using the Storage object.
     * @param filePath The filepath in which we want our data to be stored.
     */
    public Riz(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(this.storage.loadFromFile());
    }

    /**
     * The run method initialises a scanner and make sures the the correct input is
     * being fed to the Parser class. It also terminates the program when
     * the 'bye' command is given.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String dotted = "-----------------------------------";
        //greetings
        Ui.printGreetings();
        boolean running = true;

        while (running) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                running = false;
                Ui.printGoodbye();
            } else {
                Parser.parse(scanner, this.taskList, this.storage, input);
            }
        }
    }

    /**
     * Main method.
     * Initializes the RizBot with a filepath.
     * Then calls the run() method.
     * @param args Command-line arguments provided to the program.
     * These arguments can be used to customize the behavior of the program.
     */
    public static void main(String[] args) {
        Riz riz = new Riz("riz/data/riz.txt");
        riz.run();
    }
}