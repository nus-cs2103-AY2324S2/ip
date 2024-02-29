package processor;

import java.io.IOException;

import tasks.TaskList;
import ui.Ui;

/**
 * The Processor class is responsible for returning the list of tasks in the TaskList.
 */
public class UnmarkTaskProcessor extends Processor {

    /**
     * Constructor for Processor element
     * @param taskList
     * @param chatbotUi
     */
    public UnmarkTaskProcessor(TaskList taskList, Ui chatbotUi) {
        super(taskList, chatbotUi);
    }

    /**
     * Processes the given user command by delegating it to the appropriate method in the Processor class.
     * Specifically, this method processes the user command to unmark a task in the TaskList.
     * @param userInput the user command to be processed
     * @throws IOException if an I/O error occurs while processing the command
     */
    public void processCommand(String userInput) throws IOException {
        String[] array = userInput.split(" ");
        try {
            int number = Integer.parseInt(array[1]);
            taskList.unmarkTask(number - 1);
            System.out.println(chatbotUi.dividerWrapper(
                    chatbotUi.unmark() + "\n" + taskList.getTaskAtIndex(number - 1)));
            storage.writeToFile(taskList);
        } catch (NumberFormatException e) {
            System.out.println(chatbotUi.dividerWrapper("You've got to use a number to unmark a task, honey!"));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(chatbotUi.dividerWrapper("You must select a number within the size of the Task List ya."));
        } catch (IOException e) {
            System.out.println(chatbotUi.dividerWrapper("Error writing to file in storage."));
        }
    }
}
