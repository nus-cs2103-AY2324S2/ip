package main;

import utilities.Ui;

/**
 * The main class representing the RyanGosling chatbot.
 */
public class RyanGosling {

    /** The name of the chatbot. */
    public static final String CHATBOT_NAME = "RyanGosling";

    /** The dispatcher for handling user input and managing tasks. */
    private final Ui botDispatcher;

    /**
     * Constructs a new instance of RyanGosling and initializes the botDispatcher.
     */
    public RyanGosling() {
        botDispatcher = new Ui();
        botDispatcher.oneTimeLoadAllTasks();
    }

    /**
     * Gets the response from the chatbot based on the user input.
     *
     * @param userInput The user's input.
     * @return The response from the chatbot.
     */
    public MainResponseCategorized getResponse(String userInput) {
        return botDispatcher.performTaskFromSingleUserInput(userInput);
    }
}
