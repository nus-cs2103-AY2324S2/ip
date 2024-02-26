package patrick;

import java.io.File;

public class Patrick {
    private static TaskList tasks;
    private Command currentCommand;

    private Ui ui;

    private String filePath = "data/Patrick.txt";

    Patrick() {
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
     * @throws PatrickException
     */
    public String getResponse(String input) throws PatrickException {
        try {
            tasks = Storage.load();
            Command cmd = ui.getCommand(input);
            return cmd.execute(tasks, input);
        } catch (PatrickException e) {
            return e.getMessage();
        }
    }
}
