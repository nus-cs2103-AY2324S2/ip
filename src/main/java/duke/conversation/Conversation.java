package duke.conversation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class Conversation {

    protected Hashtable<String, List<String>> dialogues;
    private final Random random;

    public Conversation(String username) {
        dialogues = new Hashtable<>();
        random = new Random();
        initializeDialogues(username);
    }

    public void addDialogue(String key, String response) {
        key = key.toLowerCase();
        dialogues.computeIfAbsent(key, k -> new ArrayList<>()).add(response);
    }

    public void initializeDialogues(String username) {
        addDialogue("bye", "Bye bye! See you later!");
        addDialogue("hello", "Hi there! How can I help you today?");
        addDialogue("hello", "Greetings! What can I do for you?");
        addDialogue("hey", "Hey! What's up?");
        addDialogue("how are you", "I'm just a computer program, but thanks for asking!");
        addDialogue("what's your name", "I'm Sophia. And you?");
    }

    public String printDialogue(String message) {
        StringBuilder dialogueMessage = new StringBuilder();
        List<String> dialoguesList = dialogues.get(message.toLowerCase());
        if (dialoguesList != null && !dialoguesList.isEmpty()) {
            String dialogue = dialoguesList.get(random.nextInt(dialoguesList.size()));
            dialogueMessage.append(dialogue);
        } else {
            dialogueMessage.append("Sorry, I don't understand what you mean by '").append(message)
                    .append("'.\nMaybe try checking the spelling or ask me anything else!");
        }
        return dialogueMessage.toString();
    }
}
