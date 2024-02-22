package linus;

import java.util.Scanner;

/**
 * Main class for running Linus chatbot.
 */
public class Linus {
    private final Ui ui = new Ui();
    private final Storage storage = new Storage();
    private final TaskList taskList = new TaskList();

    /**
     * Constructs Linus object for Linus Application
     */
    public Linus() {

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        Parser parser = new Parser(this.taskList, this.ui, this.storage);
        parser.parseCommand(command);
    }

    public static void main(String[] args) {
        new Linus().run();
    }

    /**
     * Returns response in String using user input to the GUI.
     */
    public String getResponse(String input) {
        Parser parser = new Parser(this.taskList, this.ui, this.storage);
        String outputResponse = parser.parseCommand(input);

        return outputResponse;
    }
}
