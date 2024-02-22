package processor;

import tasks.TaskList;
import ui.Ui;

public class SortTaskProcessor extends Processor {

    /**
     * Constructor for Processor element
     * @param taskList
     * @param chatbotUi
     */
    public SortTaskProcessor(TaskList taskList, Ui chatbotUi) {
        super(taskList, chatbotUi);
    }

    /**
     * Processes the given user command by delegating it to the appropriate method in the Processor class.
     * Specifically, this method processes the user command to sort the TaskList.
     * @param userInput the user command to be processed
     */
    public void processCommand(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length != 2) {
            System.out.println(chatbotUi.dividerWrapper("Please enter a valid sort command!"));
            return;
        }
        String sortTypeStr = parts[1];
        TaskList.SortType sortType;
        switch (sortTypeStr) {
        case "alphabetical":
            sortType = TaskList.SortType.ALPHABETICAL;
            break;
        case "start_date":
            sortType = TaskList.SortType.START_DATE;
            break;
        case "end_date":
            sortType = TaskList.SortType.END_DATE;
            break;
        case "task_type":
            sortType = TaskList.SortType.TASK_TYPE;
            break;
        case "mark":
            sortType = TaskList.SortType.MARK;
            break;
        default:
            System.out.println("Invalid sort type."
                    + " Please enter one of 'alphabetical',"
                    + " 'start_date', 'end_date', 'task_type'.");
            return;
        }
        taskList.sortTaskList(sortType);

        System.out.println(chatbotUi.dividerWrapper(
                "Here are the tasks sorted by "
                        + sortTypeStr + ":\n" + taskList.showList()));
    }
}
