package bozo;

import java.util.Scanner;

/**
 * The Bozo class is the main class for the Bozo chatbot.
 * It contains the main method and the run method.
 */
public class Bozo {
    private static TaskList list;
    private static Storage storage;
    private static Ui ui;
    private static Parser parser;

    /**
     * Constructor for the Bozo class.
     * @param filePath The file path to save the list to.
     */
    public Bozo(String filePath) {
        storage = new Storage(filePath);
        list = new TaskList(storage.loadFile());
        ui = new Ui();
        parser = new Parser();
    }

     /**
     * The main method for the Bozo chatbot.
     */
    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            input = sc.nextLine();
            try {
                parser.parseCommand(input, list);
            } catch (BozoException e) {
                ui.showError(e.getMessage());
            }
        } while (!input.equals("bye"));

        ui.showGoodbye();
        storage.saveList(list);
    }

    public static void main(String[] args) {
        new Bozo("./data/bozo.txt").run();
    }
}
