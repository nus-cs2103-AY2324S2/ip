package pyrite;

import pyrite.command.Command;
import pyrite.command.ExitCommand;

public class Pyrite {
    private static final String NAME = "Pyrite";

    TaskList tasks = new TaskList();
    StateFile file = new StateFile();
    UserInterface ui = new UserInterface();
    public void begin() {
        ui.greet(Pyrite.NAME);
        // Load list from file
        this.tasks = file.loadState(this.tasks);
        while (true) {
            String commandString = ui.readCommand();
            Command command = Parser.parse(commandString);
            String response = command.execute(this.tasks, this.file);
            ui.say(response);
            if (command instanceof ExitCommand) {
                break;
            }
        }
    }
}