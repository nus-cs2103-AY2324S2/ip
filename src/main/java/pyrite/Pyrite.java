package pyrite;

import pyrite.command.Command;
import pyrite.command.ExitCommand;

public class Pyrite {
    private static final String NAME = "Pyrite";

    TaskList tasks = new TaskList();
    StateFile file = new StateFile();
    public Pyrite() {
        this.tasks = file.loadState(this.tasks);
    }

    protected String getResponse(String input) {
        Command command = Parser.parse(input);
        if (command instanceof ExitCommand) {
            //exit program
            System.exit(0);
        }
        String response = command.execute(this.tasks, this.file);

        return response;
    }

    protected String getName() {
        return NAME;
    }
}