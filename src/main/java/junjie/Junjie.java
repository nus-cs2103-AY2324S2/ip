package junjie;

import java.util.Scanner;

import junjie.commands.Command;

/**
 * Represents the main class of the application.
 */
public class Junjie {
    private static final String NAME = "jun jie";
    private static final String INTRO_MSG = "hi bro, im " + NAME + "\nwhat you want me to do?";

    private final TaskList taskList;
    private final Ui ui;
    private boolean isExit = false;

    /**
     * Constructor for Junjie.
     */
    public Junjie() {
        Storage.init();
        String fileContents = Storage.read();
        taskList = new TaskList(fileContents);
        ui = new Ui();
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);

        ui.print(INTRO_MSG);
        do {
            String input = scanner.nextLine();
            ui.print(getResponse(input));

        } while (!isExit);
    }

    /**
     * Gets the exit status of the chatbot.
     *
     * @return The exit status of the chatbot.
     */
    public boolean getExit() {
        return isExit;
    }

    /**
     * Gets the response from the chatbot.
     *
     * @param input The input from the user.
     * @return The response from the chatbot.
     */
    public String getResponse(String input) {
        assert input != null : "Input is null";

        Command command = Parser.handleInput(input, ui, taskList);
        if (command.isExit()) {
            isExit = true;
        }
        return command.execute(taskList, ui);
    }

    /**
     * Starts Duke chatbot on command line.
     * Entry point of application.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Junjie().run();
    }
}
