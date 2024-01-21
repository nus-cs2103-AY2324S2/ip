package controller;

import model.Task;

import java.util.ArrayList;

public class HandleUserInput {
    private final String input;
    public HandleUserInput(String input) {
        this.input = input;
    }

    public void execute(ArrayList<Task> taskList) {
        switch(this.input) {
            case "list":
                ListTask listTaskController = new ListTask(taskList);
                listTaskController.execute();
                return;
            default:
                Task task = new Task(this.input);
                AddTask addTaskController = new AddTask(task, taskList);
                addTaskController.execute();
        }
    }
}
