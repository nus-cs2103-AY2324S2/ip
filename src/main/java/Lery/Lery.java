package Lery;


import java.io.IOException;
import java.util.Scanner;

/**
 * Lery is a chatbot program that is used to save tasks
 *
 * @author  Lin Shuang Shuang
 * @version 1.0
 * @since   2024-01-25
 */

public class Lery {

    private Storage storage;
    private Ui ui;

    public Lery() {
        this.ui = new Ui();
        this.storage = new Storage();
    }


    /**
     * Runs the chatbot and parses the users comments.
     * @return Nothing.
     */

    public void run() {

        ui.greet();
        Scanner scanner = new Scanner(System.in);
        try {
            this.storage.loadTasks();
        } catch (LeryException e) {
            ui.printMessageWithLine(e.getMessage());
        }
        while(scanner.hasNext()) {
            Parser p = new Parser(this.storage);
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                ui.exit();
                break;
            }
            try {
                ui.printMessageWithLine(p.parseCommand(command));
            } catch (LeryException e) {
                ui.printMessageWithLine(e.getMessage());
            }
        }
    }

    /**
     * This is the main method which starts the chatbot.
     * @param args Unused.
     * @return Nothing.
     */
    public static void main(String[] args) {
        Lery Lery = new Lery();
        Lery.run();
    }

}
