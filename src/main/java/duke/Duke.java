package duke;

import exceptions.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A CLI chatbot with the ability to save tasks.
 */
public class Duke {
    private Storage storage;
    private duke.TaskList tasks;
    private duke.Ui ui;

    public Duke(String filePath) {
        ui = new duke.Ui();
        ui = new duke.Ui();
        storage = new Storage(filePath);
        try {
            tasks = new duke.TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            tasks = new duke.TaskList(new ArrayList<>());
        }
    }

    /**
     * Runs the program.
     * @throws DukeException when an invalid command is given.
     */
    public void run() throws DukeException {
            this.ui.showWelcomeMessage();
            this.storage.printList();
            this.ui.showStartingQn();
            this.ui.breakLines();
            Scanner sc = new Scanner(System.in);
            Parser.parse(sc, this.ui, this.tasks, this.storage);
            this.ui.showExitMessage();
            this.ui.breakLines();
    }

    public static void main(String[] args) {
        try {
            new Duke("duke.txt").run();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}

