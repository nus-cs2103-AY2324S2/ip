package duke;

import duke.storage.Storage;
import duke.ui.Cli;
import duke.ui.Ui;

public class Duke {
    public static void main(String[] args) {
        // Initialise stored tasks
        Storage.initialiseStorage();

        // Print CLI UI
        Ui ui = new Cli();
        ui.startUI();
    }
}
