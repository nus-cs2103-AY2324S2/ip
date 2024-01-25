import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Optional;

public class Coat {
    public static void main(String[] args) {
        System.out.printf(Messages.WELCOME);

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        Command currentCommand = null;

        while (true) {
            System.out.printf("\n-> ");
            String userInput = scanner.nextLine();

            // Parse user input
            Command parsedCommand = CommandParser.parseInput(userInput);

            // Execute the command
            if (parsedCommand != null) {
                currentCommand = parsedCommand;
                currentCommand.execute(tasks);
            }

            // Exit condition
            if (parsedCommand.getCommand().equalsIgnoreCase("bye")) {
                System.out.printf(Messages.EXIT);
                break;
            }
        }

        scanner.close();
    }
}
