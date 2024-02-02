package lex;

import java.util.Scanner;

import lex.parser.Parser;
import lex.parser.command.Command;
import lex.storage.Storage;
import lex.tasks.TaskList;
import lex.ui.Ui;

/**
 * Represents the main class of the Lex chatbot.
 */
public class Lex {
    final private Parser parser;
    final private Ui ui;

    /**
     * Constructor for the Lex class.
     *
     * @param filePath The file path of the data file.
     */
    public Lex(String filePath) {
        ui = new Ui(new Scanner(System.in));
        Storage storage = new Storage(filePath);

        TaskList tasks;
        try {
            tasks = storage.load();
        } catch (Exception e) {
            ui.print(e.getMessage());
            tasks = new TaskList();
        }

        parser = new Parser(tasks, ui, storage);
    }

    /**
     * Runs the Lex chatbot.
     */
    public void run() {
        ui.welcome();

        while (true) {
            try {
                String input = ui.read();
                Command command = parser.parse(input);

                if (command.execute()) {
                    break;
                }
            } catch (Exception e) {
                ui.print(e.getMessage());
            }
        }

        ui.dispose();
    }

    /**
     * The main method.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Lex("./data.json").run();
    }
}
