package pyrite;

import pyrite.command.Command;
import pyrite.command.ExitCommand;

public class Pyrite {
    static String name = "Pyrite";

    TaskList list = new TaskList();
    StateFile file = new StateFile();
    UserInterface ui = new UserInterface();
    public void begin() {
        ui.greet(Pyrite.name);
        // Load list from file
        this.list = file.loadState(this.list);
        while (true) {
            String commandString = ui.readCommand();
            Command command = Parser.parse(commandString);
            String response = command.execute(this.list, this.file);
            ui.say(response);
            if (command instanceof ExitCommand) {
                break;
            }
        }
    }
}