package duke;

import duke.command.Command;
import java.util.*;

public class Duke {
    public Duke() {
    }

    public void launch() {
        System.out.println(Ui.logo());
        System.out.println(Ui.greet());
        getUserInput();
    }

    public void getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {  // Use try-with-resources for scanner
            while (true) {  // Use a continuous loop
                System.out.print("Enter a command (or 'bye' to exit): ");
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    System.out.println(Ui.exit());
                    break;  // Exit the loop
                } else {
                    Command command = new Command(input);
                    System.out.println(command.getCommand());
                }
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.launch();
    }
}