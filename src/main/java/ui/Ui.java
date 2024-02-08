package ui;
import msg.Msg;
import msg.StdMsgs;
import task.Task;
import tasklist.TaskList;

/** Class represents interacting with the user */
public class Ui {
    private TaskList taskList;
    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public void showLoadingError() {
        new Msg("My apologies, it seems as though I have trouble loading the data").print();
    }

    public void deleteResponse(int i) {
        new Msg(
                "Got it. I've deleted this task:\n" +
                        taskList.getTask(i) +
                        "\n" +
                        String.format("Now you have %d tasks in the list.", taskList.getTaskCount() - 1)
        ).print();
    }
}
