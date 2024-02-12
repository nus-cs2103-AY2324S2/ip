
package processor;

import tasks.TaskList;
import ui.Ui;

public class Factory {
    private TaskList taskList;
    private Ui chatbotUi;

    public Factory(TaskList taskList, Ui chatbotUi) {
        this.taskList = taskList;
        this.chatbotUi = chatbotUi;
    }

    public AddTaskProcessor createAddTaskProcessor() {
        return new AddTaskProcessor(taskList, chatbotUi);
    }

    public DeleteTaskProcessor createDeleteTaskProcessor() {
        return new DeleteTaskProcessor(taskList, chatbotUi);
    }

    // public ExitProcessor createExitProcessor() {
    //     return new ExitProcessor(taskList, chatbotUi);
    // }

    public FindTaskProcessor createFindTaskProcessor() {
        return new FindTaskProcessor(taskList, chatbotUi);
    }

    public ListTasksProcessor createListTasksProcessor() {
        return new ListTasksProcessor(taskList, chatbotUi);
    }

    public MarkUnMarkTaskProcessor createMarkUnmarkTaskProcessor() {
        return new MarkUnMarkTaskProcessor(taskList, chatbotUi);
    }

}