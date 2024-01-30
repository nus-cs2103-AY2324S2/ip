package kervyn;

import kervyn.Tasks.TaskList;

import java.util.Objects;
import java.util.Scanner;

/**
 * The Ui class is responsible for handling user interactions.
 * It captures user input and displays output from the chatbot.
 */
public class Ui {

    private final String CHATBOTNAME = "Kervyn";

    /**
     * Constructs a Ui instance.
     */
    public Ui() {}

    /**
     * Starts the chatbot interaction loop. It takes user input, processes it using the Parser,
     * and continues until the user inputs 'bye'.
     *
     * @param taskList The TaskList object containing the list of tasks.
     * @param storage  The Storage object used for reading and writing tasks.
     */
    public void startChatBot(TaskList taskList, Storage storage) {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser(storage);
        System.out.println("\tHello! I'm " + this.CHATBOTNAME);
        System.out.println("\tWhat can I do for you?");
        String userInput;

        do {
            userInput = scanner.nextLine();
            parser.deduceCommand(userInput, taskList);
        } while (!Objects.equals(userInput, "bye"));
    }
}
