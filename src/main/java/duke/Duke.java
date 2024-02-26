package duke;

import java.io.File;

public class Duke {
    private static TaskList tasks;
    private Command currentCommand;

    private Ui ui;

    private String filePath = "data/Duke.txt";

    Duke() {
        this.tasks = new TaskList();
        this.ui = new Ui();
        Storage.createFile();
        File file = new File(filePath);
        assert file.exists();

    }


    /**
     * Get user input and execute respective command.
     * @param input
     * @return  String generated from execution of command.
     * @throws DukeException
     */
    public String getResponse(String input) throws DukeException {
        try {
            tasks = Storage.load();
            Command cmd = ui.getCommand(input);
            return cmd.execute(tasks, input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
