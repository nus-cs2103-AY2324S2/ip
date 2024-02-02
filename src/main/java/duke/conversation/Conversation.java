package duke.conversation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Conversation {
    public static final String INDENTATION = "      ";
    public static final String LINE = "    -----------------------------------------------------------------------------------------";


    protected Hashtable<String, List<String>> dialogues;

    public Conversation(String username) {
        dialogues = new Hashtable<>();
        initializeDialogues(username);
    }

    public Conversation() {}

    public void addDialogue(String key, String response) {
        key = key.toLowerCase();
        dialogues.computeIfAbsent(key, k -> new ArrayList<>()).add(response);
    }

    public void initializeDialogues(String username) {
        addDialogue("starter", "Hello, " + username + ". Nice to meet you!\n" + Conversation.INDENTATION + "So, what can I do for you today?");
        addDialogue("bye", "Bye bye! See you later!");
        addDialogue("hello", "Hi there! How can I help you?");
        addDialogue("hello", "Greetings! What brings you here?");
        addDialogue("hey", "Hi! How are you?");
        addDialogue("how are you", "I'm just a computer program, but thanks for asking!");
        addDialogue("what's your name", "I'm Sophia. And I bet you have a pretty name. Tell me yours?");
        addDialogue("what's your name", "I'm Sophia. And you?");
    }

    public List<String> getCommands(String key) {
        key = key.toLowerCase();
        return dialogues.get(key);
    }

    public void printDialogue(String message) {
        System.out.println(Conversation.LINE);
        List<String> dialoguesList = getCommands(message);
        if (dialoguesList != null && !dialoguesList.isEmpty()) {
            for (String dialogue : dialoguesList) {
                System.out.println(INDENTATION + dialogue);
            }
        } else {
            System.out.println(INDENTATION + "Sorry, I don't understand what you mean by " + message);
            System.out.println(INDENTATION + "Maybe try checking the spelling or ask me anything else!");
        }
        System.out.println(LINE);
    }
}
