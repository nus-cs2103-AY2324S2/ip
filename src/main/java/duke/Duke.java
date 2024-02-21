package duke;

public class Duke {
    private static TaskList tasks;
    private Command currentCommand;

    private Ui ui;

    Duke() {
        this.tasks = new TaskList();
        this.ui = new Ui();

    }




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
