package duke;

import exceptions.DukeException;
import exceptions.InvalidTaskNameException;
import task.TaskList;

import java.util.Scanner;

/**
 *  This UI class contains methods that print out chatbot
 *  messages in the console to the user.
 */
public class Parser extends Ui {

    public Parser(TaskList tasks) {
        super(tasks);
    }

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
                    super.printList();
                    break;
                case "mark":
                    super.printMark(userInput);
                    break;
                case "unmark":
                    super.printUnmark(userInput);
                    break;
                case "delete":
                    super.printDelete(userInput);
                    break;
                case "todo":
                    super.printTodo(userInput);
                    break;
                case "deadline":
                    super.printDeadline(userInput);
                    break;
                case "event":
                    super.printEvent(userInput);
                    break;
                default:
                    // if user entered input that cannot be recognised.
                    throw new DukeException("Ooink oink! I'm sorry, I don't understand.\n"
                            + "Type 'help' for command info!\n");
                }
            } catch (DukeException | InvalidTaskNameException e) {
                super.printError(e.getMessage());
            }
            userInput = scan.nextLine(); // Scan for next input.
        }
        // if user entered "bye", close scanner, save list and exit chatbot.
        scan.close();
        super.printBye();
    }
}
