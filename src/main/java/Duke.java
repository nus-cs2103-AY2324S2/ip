import Exceptions.DukeException;
import Exceptions.InvalidTaskNameException;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  This class contains the main method for the chatbot, ChrisP Bacon.
 *  ChrisP Bacon is a chatbot that manages the user's list of tasks.
 */
public class Duke {
    protected final File list = new File("data/list.txt");
    protected static final ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) {
        Gui GUI = new Gui();
        Scanner scan = new Scanner(System.in);
        SaveAndLoad saveAndLoad = new SaveAndLoad();

        // Entering the chatbot and load list from hard disk.
        saveAndLoad.loadList();

        // Get user's first input.
        GUI.printIntro();
        String userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            try {
                String firstWord = userInput.indexOf(' ') < 0
                        ? userInput
                        : userInput.substring(0, userInput.indexOf(' '));

                switch (firstWord) {
                case "help":
                    GUI.printHelp();
                    break;
                case "list":
                    GUI.printList();
                    break;
                case "mark":
                    GUI.printMark(userInput);
                    break;
                case "unmark":
                    GUI.printUnmark(userInput);
                    break;
                case "delete":
                    GUI.printDelete(userInput);
                    break;
                case "todo":
                    GUI.printTodo(userInput);
                    break;
                case "deadline":
                    GUI.printDeadline(userInput);
                    break;
                case "event":
                    GUI.printEvent(userInput);
                    break;
                default:
                    // if user entered input that cannot be recognised.
                    throw new DukeException("Ooink oink! I'm sorry, I don't understand.\n"
                            + "Type 'help' for command info!\n");
                }
            } catch (DukeException | InvalidTaskNameException e) {
                GUI.printError(e.getMessage());
            }
            userInput = scan.nextLine(); // Scan for next input.
        }

        // if user entered "bye", close scanner, save list and exit chatbot.
        scan.close();
        saveAndLoad.saveList();
        GUI.printBye();
    }
}
