package duke;

import duke.exceptions.DukeException;
import duke.task.Task;
import duke.util.Parser;
import duke.util.Storage;
import duke.task.TaskList;
import duke.util.Ui;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList todo;
    private final Ui ui;

    // Duke Constructor
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            todo = new TaskList(storage.readFile());
        } catch (IOException e) {
            ui.showLoadingError();
            todo = new TaskList();
        }
    }



}
