package duke;

import duke.tasks.TaskList;

import java.util.Scanner;

/**
 * Represents a chat bot to keep track of user's duke.tasks.
 */
public class Duke {

    private static TaskList list = new TaskList();
    private Ui ui;

    public static void main(String[] args) {
        new Duke().run();
    }

    public Duke() {
        ui = new Ui();
        try {
            Storage.loadFileContents(list);
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser();
        ui.showIntro();

        String original = sc.nextLine();

        while (!original.equals("bye")) {
            try {
                parser.processLine(original, list, ui);
            } catch (DukeException e) {
                ui.showLoadingError(e.getMessage());
            }
            original = sc.nextLine();
        }

        try {
            Storage.writeToFile(list);
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
        }
        ui.showOutro();
        sc.close();
    }
}


