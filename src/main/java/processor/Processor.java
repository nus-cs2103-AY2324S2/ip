package processor;

import java.io.IOException;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The Processor abstract class performs commands and defines a command function for it's children.
 * It interacts with the TaskList, Ui, and Storage classes to handle user commands and manage tasks.
 */
public abstract class Processor {
    protected TaskList taskList;
    protected Ui chatbotUi;
    protected Storage storage;

    /**
     * Constructor for AbstractProcessor element
     * @param taskList the list of tasks
     * @param chatbotUi the user interface
     */
    public Processor(TaskList taskList, Ui chatbotUi) {
        this.taskList = taskList;
        this.chatbotUi = chatbotUi;
        this.storage = new Storage("src/data/tasks.txt", taskList);
    }

    /**
     * Process the user input command
     * @param userInput the user input command
     */
    public abstract void processCommand(String userInput) throws IOException;
}
