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
    public boolean shouldContinueSession;

    ChatSession(TaskList taskList, Ui ui) {
        this.shouldContinueSession = true;
        this.ui = ui;
        this.parser = new Parser(this);
        this.taskList = taskList;
    }

    public void printMessage(String message) {
        ui.printDukeMessage(message);
    }

    public void handleMessage(String message) {
        this.parser.handleMessage(message);
    }

    public void terminateChat() {
        this.shouldContinueSession = false;
    }
}
