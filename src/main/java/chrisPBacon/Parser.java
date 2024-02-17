package chrisPBacon;

import exceptions.ChrisPBaconException;
import exceptions.InvalidTaskNameException;
import task.TaskList;

import java.util.Scanner;

/**
 *  This class makes sense of the user's inputs.
 */
public class Parser extends Ui {
    private final TaskList tasks;

    /**
     * Constructor for Parser.
     *
     * @param taskList to be modified.
     */
    public Parser(TaskList taskList) {
        this.tasks = taskList;
    }

    /**
     * Deals with making sense of the user command.
     */
    public void parse() {
        super.printIntro();
        String userInput = "";
        Scanner scan = new Scanner(System.in);

        userInput = scan.nextLine();
        while (!userInput.equals("bye")) {
            try {
                String firstWord = userInput.indexOf(' ') < 0
                        ? userInput
                        : userInput.substring(0, userInput.indexOf(' '));

                switch (firstWord) {
                case "help":
                    super.printHelp();
                    break;
                case "list":
                    super.printList(this.tasks);
                    break;
                case "mark":
                    super.printMark(userInput, this.tasks);
                    break;
                case "unmark":
                    super.printUnmark(userInput, this.tasks);
                    break;
                case "delete":
                    super.printDelete(userInput, this.tasks);
                    break;
                case "todo":
                    super.printTodo(userInput, this.tasks);
                    break;
                case "deadline":
                    super.printDeadline(userInput, this.tasks);
                    break;
                case "event":
                    super.printEvent(userInput, this.tasks);
                    break;
                default:
                    // if user entered input that cannot be recognised.
                    throw new ChrisPBaconException("Ooink oink! I'm sorry, I don't understand.\n"
                            + "Type 'help' for command info!\n");
                }
            } catch (ChrisPBaconException | InvalidTaskNameException e) {
                super.printError(e.getMessage());
            }
            userInput = scan.nextLine(); // Scan for next input.
        }
        // if user entered "bye", close scanner, save list and exit chatbot.
        scan.close();
        super.printBye();
    }
}
