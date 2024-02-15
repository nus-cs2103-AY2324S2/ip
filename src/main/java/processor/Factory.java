
package processor;

import tasks.TaskList;
import ui.Ui;

/**
 * The Factory class represents a factory that creates different processors for task operations.
 * It provides methods to create processors for adding, deleting, finding, listing, and marking/unmarking tasks.
 */
public class Factory {
    private TaskList taskList;
    private Ui chatbotUi;

    /**
     * Constructs a Factory object with the specified task list and chatbot UI.
     * This generates a compartmentalization of all the processor methods.
     * @param taskList the task list to be used by the processors
     * @param chatbotUi the chatbot UI to be used by the processors
     */
    public Factory(TaskList taskList, Ui chatbotUi) {
        this.taskList = taskList;
        this.chatbotUi = chatbotUi;
    }

    /**
     * Creates an AddTaskProcessor object.
     *
     * @return the created AddTaskProcessor object
     */
    public AddTaskProcessor createAddTaskProcessor() {
        return new AddTaskProcessor(taskList, chatbotUi);
    }

    /**
     * Creates a DeleteTaskProcessor object.
     *
     * @return the created DeleteTaskProcessor object
     */
    public DeleteTaskProcessor createDeleteTaskProcessor() {
        return new DeleteTaskProcessor(taskList, chatbotUi);
    }

    /**
     * Creates a FindTaskProcessor object.
     *
     * @return the created FindTaskProcessor object
     */
    public FindTaskProcessor createFindTaskProcessor() {
        return new FindTaskProcessor(taskList, chatbotUi);
    }

    /**
     * Creates a ListTasksProcessor object.
     *
     * @return the created ListTasksProcessor object
     */
    public ListTasksProcessor createListTasksProcessor() {
        return new ListTasksProcessor(taskList, chatbotUi);
    }

    /**
     * Creates a MarkUnMarkTaskProcessor object.
     *
     * @return the created MarkUnMarkTaskProcessor object
     */
    public MarkUnMarkTaskProcessor createMarkUnmarkTaskProcessor() {
        return new MarkUnMarkTaskProcessor(taskList, chatbotUi);
    }
}