package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.greet();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while (!(command = br.readLine()).equals("bye")) {
            try {
                Parser.checkCmd(this.tasks, command);
            } catch (DukeException de){
                Ui.beautify(de.getMessage());
            }
        }
        storage.save(tasks);
        ui.exit();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/tasks.txt").run();
    }
}