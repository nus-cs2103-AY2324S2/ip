package knight;

import java.util.Scanner;

/**
 * The main class of the program.
 */
public class Knight {
    private static TaskList tasks = new TaskList();

    public static void main(String[] args) {
        tasks = Storage.readFromFile();
        Ui.speak("Greetings, Your Excellency! Thy humble\n"
                + Ui.logo
                + "doth stand before thee. How may I serve thee on this day?");

        Scanner scan = new Scanner(System.in);
        String message;

        while (true) {
            message = scan.nextLine();
            Command command;

            try {
                command = CommandParser.parseCommand(message);
            } catch (NonstandardCommandException e) {
                Ui.speak(e.getMessage());
                continue;
            }

            if (command == Command.BYE) {
                break;
            } else {
                tasks.executeCommand(command, message);
            }
        }

        Storage.writeToFile(tasks);
        Ui.speak("Farewell, Your Excellency! May we cross paths once more in the near future.");
    }
}
