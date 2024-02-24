package processor;

import java.io.IOException;

import tasks.TaskList;
import ui.Ui;

/**
 * The Processor class is responsible for returning the list of tasks in the TaskList.
 */
public class ListTasksProcessor extends Processor {
    
    /**
     * Constructor for Processor element
     * @param taskList
     * @param chatbotUi
     */
    public ListTasksProcessor(TaskList taskList, Ui chatbotUi) {
        super(taskList, chatbotUi);
    }

    /**
     * Processes the given user command by delegating it to the appropriate method in the Processor class.
     * Specifically, this method processes the user command to list all tasks in the TaskList.
     * @throws IOException if an I/O error occurs while processing the command
     */
    public void processCommand(String userInput) throws IOException {
        // if array empty
        assert taskList != null : "Task list should not be null";

        if (taskList.size() == 0) {
            System.out.println(chatbotUi.dividerWrapper("Your list is empty"));
        }
        else {
            System.out.println(chatbotUi.dividerWrapper(
                    "Here are the tasks in your list: \n" + storage.generateTasks()));
        }
    }


}
