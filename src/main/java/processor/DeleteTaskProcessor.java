package processor;

import java.io.IOException;

import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * The DeleteTaskProcessor class represents a processor that processes
 * the user command to delete a task from the TaskList.
 */
public class DeleteTaskProcessor extends Processor {

    /**
     * Constructor for Processor element
     * @param taskList
     * @param chatbotUi
     */
    public DeleteTaskProcessor(TaskList taskList, Ui chatbotUi) {
        super(taskList, chatbotUi);
    }


    /**
     * Processes the given user command by delegating it to the appropriate method in the Processor class.
     * Specifically, this method processes the user command to delete a task from the TaskList.
     * @param userInput the user command to be processed
     * @throws IOException if an I/O error occurs while processing the command
     */
    public void processCommand(String userInput) {

        String[] array = userInput.split(" ");
        int previousSize = taskList.size();

        try {

            int number = Integer.parseInt(array[1]);
            Task temp = taskList.getTaskAtIndex(number - 1);
            taskList.deleteAtIndex(number - 1);

            assert previousSize == taskList.size() + 1 : "Task list size should have decreased";
            
            System.out.println(chatbotUi.dividerWrapper(
                    "Noted. I've removed this task:\n"
                            + temp + "\nNow you have "
                            + taskList.size() + " tasks in the list"));
            storage.writeToFile();
        } catch (NumberFormatException e) {
            System.out.println(chatbotUi.dividerWrapper("You must use a number to delete successfully."));
        } catch (IndexOutOfBoundsException e) {
            // System.out.println(e)
            if (taskList.size() == 0) {
                System.out.println(chatbotUi.dividerWrapper("Can not delete, task list is empty!"));
            }
            System.out.println(chatbotUi.dividerWrapper("You must select a number within the scope of the task list"));
        } catch (IOException e) {
            System.out.println(chatbotUi.dividerWrapper("Error writing to file in storage."));
        }
    }
}
