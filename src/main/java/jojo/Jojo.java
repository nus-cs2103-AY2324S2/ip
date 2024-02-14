package jojo;

import exceptions.JojoException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A CLI chatbot with the ability to save tasks.
 */
public class Jojo {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Jojo(String filePath) {
        ui = new Ui();
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (JojoException e) {
            this.ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the program.
     * @throws JojoException when an invalid command is given.
     */
    public void run() throws JojoException {
            ui.showWelcomeMessage();
            storage.printList();
            ui.showStartingQn();
            ui.breakLines();
            Scanner sc = new Scanner(System.in);
            Parser.parse(sc, ui, tasks, storage);
            this.ui.showExitMessage();
            this.ui.breakLines();
    }

    public String getResponse(String response) {
        return ui.getResponse(response);
    }

    public static void main(String[] args) {
        try {
            new Jojo("jojo.txt").run();
        } catch (JojoException e) {
            System.out.println(e.getMessage());
        }
    }
}

