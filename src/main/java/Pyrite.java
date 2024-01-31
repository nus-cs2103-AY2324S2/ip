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
        // Solution below inspired by
        // https://stackoverflow.com/questions/31690570/java-scanner-command-system
        // https://stackoverflow.com/questions/4822256/java-is-there-an-easy-way-to-select-a-subset-of-an-array
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