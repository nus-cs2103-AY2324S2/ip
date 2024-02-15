package processor;

import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class MarkUnMarkTaskProcessor extends Processor{

    /**
     * Constructor for Processor element
     * @param taskList
     * @param chatbotUi
     */
    public MarkUnMarkTaskProcessor(TaskList taskList, Ui chatbotUi) {
        super(taskList, chatbotUi);
    }

    /**
     * Processes the given user command by delegating it to the appropriate method in the Processor class.
     * Specifically, this method processes the user command to mark or unmark a task in the TaskList.
     * @param userInput the user command to be processed
     * @throws IOException if an I/O error occurs while processing the command
     */
    public void processCommand(String userInput) throws IOException {
        String[] array = userInput.split(" ");
        String command = array[0];

        

        try {
            int number = Integer.parseInt(array[1]);

            if (command.equals("mark")) {
                taskList.markTask(number - 1);
                System.out.println(chatbotUi.dividerWrapper(
                        chatbotUi.mark() + "\n" + taskList.getTaskAtIndex(number - 1)));
            } else if (command.equals("unmark")) {
                taskList.unmarkTask(number - 1);
                System.out.println(chatbotUi.dividerWrapper(
                        chatbotUi.unmark() + "\n" + taskList.getTaskAtIndex(number - 1)));
            }
            storage.writeToFile();
        } catch (NumberFormatException e) {
            System.out.println(chatbotUi.dividerWrapper("You must use a number to mark."));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(chatbotUi.dividerWrapper("You must select a number within the size of the Task List."));
        } catch (IOException e) {
            System.out.println(chatbotUi.dividerWrapper("Error writing to file in storage."));
        }
    }
    
}
