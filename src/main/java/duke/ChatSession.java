package duke;
import java.util.List;

import duke.commands.NamedCommand;

/**
 * ChatSession class that handles the chat session by encapsulating the UI, Parser and TaskList
 */
public class ChatSession {
    private Ui ui;
    private Parser parser;
    public TaskList taskList;
    public List<NamedCommand> commandList;
    public boolean continueSession;

    ChatSession(TaskList taskList) {
        this.continueSession = true;
        this.ui = new Ui(this);
        this.parser = new Parser(this);
        this.taskList = taskList;
    }

    public void initChat() {
        this.ui.initChat();
    } 

    public void printMessage(String message) {
        ui.printMessage(message);
    }

    public void handleMessage(String message) {
        this.parser.handleMessage(message);
    }

    public void terminateChat() {
        this.continueSession = false;
    }
}
