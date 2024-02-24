package processor;

import tasks.TaskList;
import ui.Ui;

/**
 * The FindTaskProcessor class represents a processor that processes
 * the user command to find a task in the TaskList.
 * **/
public class FindTaskProcessor extends Processor {

    /**
     * Constructor for Processor element
     * @param taskList
     * @param chatbotUi
     */
    public FindTaskProcessor(TaskList taskList, Ui chatbotUi) {
        super(taskList, chatbotUi);
    }


    /**
     * Processes the given user command by delegating it to the appropriate method in the Processor class.
     * Specifically, this method processes the user command to find a task in the TaskList.
     * @param userInput the user command to be processed
     */
    public void processCommand(String userInput) {
        String[] array = userInput.split(" ");
        String keyword = array[1];
        if (taskList.findTask(keyword).equals("")) {
            System.out.println(chatbotUi.dividerWrapper(
                    "No matching tasks in your list."));
            return;
        }
        System.out.println(chatbotUi.dividerWrapper(
                "Here are the matching tasks in your list: \n" + taskList.findTask(keyword)));
    }
}
