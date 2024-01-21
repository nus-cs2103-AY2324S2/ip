package controller;

import model.Task;
import view.MarkTaskView;

import java.util.ArrayList;

public class HandleUserInput {
    private final String input;
    public HandleUserInput(String input) {
        this.input = input;
    }

    public void execute(ArrayList<Task> taskList) {
        String[] i = input.split(" ", 2);
        switch(i[0]) {
            case "list":
                ListTask listTaskController = new ListTask(taskList);
                listTaskController.execute();
                return;
            case "mark":
                int markIndex = Integer.parseInt(i[1]) - 1;
                MarkTask markTaskController = new MarkTask(markIndex, taskList);
                markTaskController.execute();
                return;
            case "unmark":
                int unmarkIndex = Integer.parseInt(i[1]) - 1;
                UnmarkTask unmarkTaskController = new UnmarkTask(unmarkIndex, taskList);
                unmarkTaskController.execute();
                return;
            default:
                Task task = new Task(this.input);
                AddTask addTaskController = new AddTask(task, taskList);
                addTaskController.execute();
        }
    }
}
