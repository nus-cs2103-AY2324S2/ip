import exceptions.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

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

