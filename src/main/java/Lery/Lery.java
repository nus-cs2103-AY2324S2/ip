package Lery;


import java.io.IOException;
import java.util.Scanner;

/**
 * Duke is a chatbot program that is used to save tasks
 *
 * @author  Lin Shuang Shuang
 * @version 1.0
 * @since   2023-01-25
 */

public class Lery {

    private Storage storage;
    private Ui ui;
    public Lery() {
        this.ui = new Ui();
        this.storage = new Storage();

    }










    public void run() throws DukeException {

        ui.greet();
        Scanner scanner = new Scanner(System.in);
        this.storage.loadTasks();
        while(scanner.hasNext()) {
            Parser p = new Parser(this.storage);

            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                ui.exit();
                break;
            }
            try {
                ui.printMessageWithLine(p.parseCommand(command));
            } catch (DukeException e) {
                ui.printMessageWithLine(e.getMessage());
            }
        }
    }

    /**
     * This is the main method which starts the chatbot.
     * @param args Unused.
     * @return Nothing.
     * @exception IOException On input error.
     * @see IOException
     */
    public static void main(String[] args) throws DukeException {
        Lery Lery = new Lery();
        Lery.run();

    }


}
