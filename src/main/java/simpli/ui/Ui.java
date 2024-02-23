package simpli.ui;

import simpli.tasks.Task;
import simpli.tasks.TaskList;

public class Ui {

    public String getGreetingMessage() {
        return "Hello! I'm SIMP-LI\nHow can I simp-lify your life?";
    }

    public String getByeMessage() {
        return "Hope to simp for you again.";
    }

    public String getAddedTaskMessage(Task addedTask) {
        return "Great I have added the following task:\n"
                + "\t"
                + addedTask;
    }

    public String getDeletedTaskMessage(Task deletedTask) {
        return "Noted I have deleted the following task:\n" + deletedTask;
    }

    public String getFoundTasks(TaskList foundTasks) {
        return "I have found these tasks:\n" + foundTasks;
    }

    public String getListTasksMessage(TaskList taskList) {
        return "You currently have these tasks:\n" + taskList;
    }

    public String getMarkedTaskMessage(Task task) {
        return "The following task has been marked:\n" + task;
    }

    public String getUnmarkedTaskMessage(Task task) {
        return "The following task has been unmarked:\n" + task;
    }
}
