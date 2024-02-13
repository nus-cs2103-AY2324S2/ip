package seedu.duke;

import seedu.exceptions.DukeException;
import seedu.exceptions.InvalidTaskNameException;
import seedu.task.TaskList;

import java.util.Scanner;

/**
 *  This UI class contains methods that print out chatbot
 *  messages in the console to the user.
 */
public class Parser extends Ui {
    public Parser() { }

    public void parse(TaskList tasks) {
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
                    super.printList(tasks);
                    break;
                case "mark":
                    super.printMark(userInput, tasks);
                    break;
                case "unmark":
                    super.printUnmark(userInput, tasks);
                    break;
                case "delete":
                    super.printDelete(userInput, tasks);
                    break;
                case "todo":
                    super.printTodo(userInput, tasks);
                    break;
                case "deadline":
                    super.printDeadline(userInput, tasks);
                    break;
                case "event":
                    super.printEvent(userInput, tasks);
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
