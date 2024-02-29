package processor;

import java.io.IOException;

import tasks.TaskList;
import ui.Ui;



/**
 * The MarkTaskProcessor class represents a processor that processes
 * the user command to mark a task in the TaskList.
 */
public class MarkTaskProcessor extends Processor {

    /**
     * Constructor for Processor element
     * @param taskList
     * @param chatbotUi
     */
    public MarkTaskProcessor(TaskList taskList, Ui chatbotUi) {
        super(taskList, chatbotUi);
    }

    /**
     * Processes the given user command by delegating it to the appropriate method in the Processor class.
     * Specifically, this method processes the user command to mark a task in the TaskList.
     * @param userInput the user command to be processed
     * @throws IOException if an I/O error occurs while processing the command
     */
    public void processCommand(String userInput) throws IOException {
        String[] array = userInput.split(" ");

        try {
            int number = Integer.parseInt(array[1]);

            taskList.markTask(number - 1);
            System.out.println(chatbotUi.dividerWrapper(
                    chatbotUi.mark() + "\n" + taskList.getTaskAtIndex(number - 1)));
            storage.writeToFile(taskList);
        } catch (NumberFormatException e) {
            System.out.println(chatbotUi.dividerWrapper(
                    "You must use a number to mark like you marked all over me yesterday."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(chatbotUi.dividerWrapper("You must select a number within the size of the Task List dear."));
        } catch (IOException e) {
            System.out.println(chatbotUi.dividerWrapper("Error writing to file in storage."));
        }
    }
}
