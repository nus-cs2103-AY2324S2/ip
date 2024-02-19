package duke;

import java.util.ArrayList;

/**
 * ChatSession class that handles the chat session by encapsulating the UI, Parser and TaskList
 */
public class ChatSession {
    private TaskList taskList;
    private boolean shouldContinueSession;
    private ArrayList<String> responses = new ArrayList<String>();
    private Parser parser;

    ChatSession(TaskList taskList) {
        this.shouldContinueSession = true;
        this.parser = new Parser(this);
        this.taskList = taskList;
    }

    public void printMessage(String message) {
        this.responses.add(message);
    }

    public void handleMessage(String message) {
        this.parser.handleMessage(message);
    }

    public ArrayList<String> getResponses() {
        ArrayList<String> copy = new ArrayList<String>(this.responses);
        this.responses.clear();
        return copy;
    }

    public void terminateChat() {
        this.shouldContinueSession = false;
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public boolean shouldContinueSession() {
        return this.shouldContinueSession;
    }
}
