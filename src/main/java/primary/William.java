package primary;

import ui.Ui;

/**
 * Main class for this chatbot
 */
public class William {
    private Ui ui;

    /**
     * Constructor to initialise the UI, and load tasks into ArrayList
     */
    public William() {
        this.ui = new Ui();
        this.ui.loadTasks();
    }

    /**
     * Gets response from chatbot based on user input
     *
     * @param userInput The user's input
     * @return The response from the chatbot
     */
    public String getChatBotResponse(String userInput) {
        return ui.interactWithUser(userInput);
    }
}
