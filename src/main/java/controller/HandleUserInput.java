package controller;

import model.Deadline;
import model.Event;
import model.Task;
import model.ToDo;
import view.MarkTaskView;

import java.util.ArrayList;

public class HandleUserInput {
    private final String input;
    public HandleUserInput(String input) {
        this.input = input;
    }

    public void execute(ArrayList<Task> taskList) {
        String[] splitTask = input.split(" ", 2);
        switch(splitTask[0]) {
            case "list":
                ListTask listTaskController = new ListTask(taskList);
                listTaskController.execute();
                return;
            case "mark":
                int markIndex = Integer.parseInt(splitTask[1]) - 1;
                MarkTask markTaskController = new MarkTask(markIndex, taskList);
                markTaskController.execute();
                return;
            case "unmark":
                int unmarkIndex = Integer.parseInt(splitTask[1]) - 1;
                UnmarkTask unmarkTaskController = new UnmarkTask(unmarkIndex, taskList);
                unmarkTaskController.execute();
                return;
            case "todo":
            case "event":
            case "deadline":
                AddTask addTaskController = getAddTask(taskList, splitTask);
                addTaskController.execute();
                break;
        }
    }

    private static AddTask getAddTask(ArrayList<Task> taskList, String[] splitTask) {
        Task task;
        switch (splitTask[0]) {
            case "todo":
                task = parseToDo(splitTask[1]);
                break;
            case "event":
                task = parseEvent(splitTask[1]);
                break;
            case "deadline":
                task = parseDeadline(splitTask[1]);
                break;
            default:
                task = null;
        }
        return new AddTask(task, taskList);
    }

    private static ToDo parseToDo(String todo) {
        return new ToDo(todo);
    }

    private static Event parseEvent(String event) {
        String[] splitEvent = event.split(" /from ", 2);
        String[] splitDuration = splitEvent[1].split(" /to ", 2);
        String title = splitEvent[0];
        String from = splitDuration[0];
        String to = splitDuration[1];

        return new Event(title, from, to);
    }

    private static Deadline parseDeadline(String deadline) {
        String[] splitDeadline = deadline.split(" /by ", 2);
        String title = splitDeadline[0];
        String time = splitDeadline[1];
        return new Deadline(title, time);
    }
}
