import javax.imageio.IIOException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Pyrite {
    static String name = "Pyrite";
    static String horizontal_line = "\t____________________________________________________________";
    static String greeting = "\tHello! I'm " + name + "\n"
            + "\tWhat can I do for you?";
    static String farewell = "\tBye. Hope to see you again soon!";
    TaskList list = new TaskList();
    StateFile file = new StateFile();


    public void begin() {
        System.out.println(this.horizontal_line);
        System.out.println(this.greeting);
        // Load list from file
        this.list = file.loadState(this.list);
        System.out.println(this.horizontal_line);
        Scanner scanner = new Scanner(System.in);
        String input;
        // Solution below inspired by
        // https://stackoverflow.com/questions/31690570/java-scanner-command-system
        // https://stackoverflow.com/questions/4822256/java-is-there-an-easy-way-to-select-a-subset-of-an-array
        while (true) {
            System.out.println();
            input = scanner.nextLine();
            System.out.println(this.horizontal_line);
            Command command;
            command = Parser.parse(input);
            String response = command.execute(this.list, this.file);
            System.out.println(response);
            if (command instanceof ExitCommand) {
                break;
            }
            System.out.println(this.horizontal_line);
        }
    }
}