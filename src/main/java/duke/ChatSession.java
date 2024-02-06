package duke;
import java.util.List;

import duke.commands.NamedCommand;

public class ChatSession {
    private Ui ui;
    private Parser parser;
    public TaskList taskList;
    public List<NamedCommand> commandList;
    public boolean shouldContinueSession;

    ChatSession(TaskList taskList) {
        this.shouldContinueSession = true;
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
        this.shouldContinueSession = false;
    }
}
