package junjie;

import java.util.Scanner;

import junjie.commands.Command;

/**
 * Represents the main class of the application.
 */
public class Junjie {
    static final String NAME = "jun jie";
    static final String INTRO_MSG = "hi bro, im " + NAME + "\nwhat you want me to do?";

    /**
     * Constructs a new Junjie instance.
     */
    public Junjie() {
        Scanner scanner = new Scanner(System.in);

        Storage.init();
        String fileContents = Storage.read();
        TaskList taskList = new TaskList(fileContents);
        Ui ui = new Ui();

        ui.print(INTRO_MSG);
        while (true) {
            String input = scanner.nextLine();
            Command command = Parser.handleInput(input, ui, taskList);
            command.execute(taskList, ui);

            if (command.isExit()) {
                break;
            }
        }
    }

    /**
     * Entry point of the application.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Junjie();
    }
}
