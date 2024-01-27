package duke;

import duke.storage.Storage;
import duke.ui.Cli;

public class Duke {
    public static void main(String[] args) {
        // Initialise stored tasks
        Storage.initialiseStorage();

        // Print CLI UI
        Cli.printUI();
    }
}
