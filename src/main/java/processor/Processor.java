package processor;

import java.io.IOException;


import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public abstract class Processor {
    protected TaskList taskList;
    protected Ui chatbotUi;
    protected Storage storage;

    /**
     * Constructor for AbstractProcessor element
     * @param taskList
     * @param chatbotUi
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
