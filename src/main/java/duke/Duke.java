package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Duke Class is the main class that will run based on the different commands given
 * by the user. Available commands include todo, event, deadline, mark, unmark, delete.
 */
public class Duke {
    Storage storage;
    Ui ui;
    TaskList taskList;
    Parser parser = new Parser();
    Scanner scanner1;
    boolean isEnded = false;

    String[] commandList = new String[] {"bye", "mark", "unmark", "todo", "deadline", "event", "list"};

    /**
     * Constructor for the Duke Class.
     * @param filePath
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList();
    }

    /**
     * Terminates the Duke program.
     */
    public void exit() {
        this.ui.bye();
        this.scanner1.close();
        this.storage.saveFile(this.taskList);
        this.isEnded = true;
        horizontalLines();
    }

    public void horizontalLines() {
        System.out.println("\n    ____________________________________________________________");
    }

    /**
     * Sends the commands input by the user to the Parser Class for processing.
     */
    public void parse() {
        String commandInput = scanner1.nextLine();
        this.parser.input(commandInput, this, this.taskList);
    }

    /**
     * Starts up and runs the Duke program.
     */
    public void run() {
        try {
            this.taskList = storage.loadFile();
        } catch (IOException e) {
            System.out.println("Run failed.");
        }
    }

    public static void main(String[] args) {
        Duke Duke1 = new Duke("data/tasks.txt");
        Duke1.run();
        Duke1.scanner1 = new Scanner(System.in);
        Duke1.horizontalLines();
        Duke1.ui.greeting();

        while (!Duke1.isEnded) {
            Duke1.horizontalLines();
            Duke1.parse();
            //Duke1.horizontalLines();
        }
    }
}
