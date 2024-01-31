import javax.imageio.IIOException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
public class Pyrite {
    static String name = "Pyrite";

    TaskList list = new TaskList();
    StateFile file = new StateFile();
    UserInterface ui = new UserInterface();
    public void begin() {
        ui.greet(Pyrite.name);
        // Load list from file
        this.list = file.loadState(this.list);
        String input;
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