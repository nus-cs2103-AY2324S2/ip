package duke.conversation;

import duke.ui.Ui;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * The Conversation class manages dialogues and responses for interacting with the user.
 * It provides predefined dialogues and handles the display of responses.
 */
public class Conversation {

    protected Hashtable<String, List<String>> dialogues;

    /**
     * Constructs a Conversation instance with an optional username for personalized dialogues.
     *
     * @param username The optional username for personalized dialogues.
     */
    public Conversation(String username) {
        dialogues = new Hashtable<>();
        initializeDialogues(username);
    }

    /**
     * Adds a dialogue and response pair to the conversation.
     *
     * @param key      The key associated with the dialogue.
     * @param response The response corresponding to the key.
     */
    public void addDialogue(String key, String response) {
        key = key.toLowerCase();
        dialogues.computeIfAbsent(key, k -> new ArrayList<>()).add(response);
    }

    /**
     * Initializes predefined dialogues based on the provided or default username.
     *
     * @param username The optional username for personalized dialogues.
     */
    public void initializeDialogues(String username) {
        addDialogue("starter", "Hello, " + username +
                ". Nice to meet you!\n" + Ui.INDENTATION +
                "So, what can I do for you today?");
        addDialogue("bye", "Bye bye! See you later!");
        addDialogue("hello", "Hi there! How can I help you?");
        addDialogue("hello", "Greetings! What brings you here?");
        addDialogue("hey", "Hi! How are you?");
        addDialogue("how are you", "I'm just a computer program, " +
                "but thanks for asking!");
        addDialogue("what's your name", "I'm Sophia. And I bet you " +
                "have a pretty name. Tell me yours?");
        addDialogue("what's your name", "I'm Sophia. And you?");
    }

    /**
     * Retrieves the list of responses associated with a given key.
     *
     * @param key The key associated with the desired dialogues.
     * @return The list of responses for the given key.
     */
    public List<String> getCommands(String key) {
        key = key.toLowerCase();
        return dialogues.get(key);
    }

    /**
     * Prints the dialogues or an error message based on the user's input.
     *
     * @param message The user's input used to retrieve corresponding dialogues.
     */
    public String printDialogue(String message) {
        StringBuilder dialogueMessage = new StringBuilder();
        List<String> dialoguesList = getCommands(message);
        if (dialoguesList != null && !dialoguesList.isEmpty()) {
            for (String dialogue : dialoguesList) {
                dialogueMessage.append(dialogue).append("\n");
            }
        } else {
            dialogueMessage.append("Sorry, I don't understand what you mean by ").append(message).append("\n")
                    .append("Maybe try checking the spelling or ask me anything else!\n");
        }
        return dialogueMessage.toString();
    }
}
