package chatbot.task;

import chatbot.exception.DukeException;
import chatbot.storage.Storage;
import chatbot.ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        ArrayList<Task> newTaskList = new ArrayList<>();
        this.tasks = newTaskList;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    public String printTask(int taskNum) {
        return tasks.get(taskNum - 1).printTask();
    }

    public void markTask(int taskNum) throws DukeException {
        if (taskNum == 0) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "task 0? how can i mark a task that doesn't exist?!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        } else if (taskNum > tasks.size()) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "hahaha! you only have " + tasks.size() + " tasks in your task list!!\n"
                    + "there's no task " + taskNum + "!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        }
        tasks.get(taskNum - 1).complete();
        Storage.saveData(tasks);
        Ui.printMarkedTask(this, taskNum);
    }

    public void unmarkTask(int taskNum) throws DukeException {
        if (taskNum == 0) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "task 0? how can i unmark a task that doesn't exist?!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        } else if (taskNum > tasks.size()) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "hahaha! you only have " + tasks.size() + " tasks in your task list!!\n"
                    + "there's no task " + taskNum + "!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        }
        tasks.get(taskNum - 1).unmark();
        Storage.saveData(tasks);
        Ui.printUnmarkedTask(this, taskNum);
    }

    public void deleteTask(int taskNum) throws DukeException {
        if (taskNum == 0) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "error: there's no such thing as task 0!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        } else if (taskNum > tasks.size()) {
            String exceptionMessage = Ui.createLine() + "\n"
                    + "error! you only have " + tasks.size() + " tasks in your task list!!\n"
                    + "there's no task " + taskNum + "!\n"
                    + Ui.createLine();
            throw new DukeException(exceptionMessage);
        }
        Task deletedTask = tasks.get(taskNum - 1);
        String deletedTaskMessage = deletedTask.printTask();
        tasks.remove(taskNum - 1);
        int remainingNumOfTasks = tasks.size();
        Storage.saveData(tasks);
        Ui.printDeletedTask(deletedTaskMessage, remainingNumOfTasks);
    }

    public void addTodoTask(String name) {
        ToDo addedTask = new ToDo(name);
        tasks.add(addedTask);
        int totalNumOfTasks = tasks.size();
        Ui.printAddedTask(addedTask.printTask(), totalNumOfTasks);
        Storage.saveData(tasks);
    }

    public void addDeadlineTask(String name, LocalDateTime deadline) {
        Deadline addedTask = new Deadline(name, deadline);
        tasks.add(addedTask);
        int totalNumOfTasks = tasks.size();
        Ui.printAddedTask(addedTask.printTask(), totalNumOfTasks);
        Storage.saveData(tasks);
    }

    public void addEventTask(String name, LocalDateTime start, LocalDateTime end) {
        Event addedTask = new Event(name, start, end);
        tasks.add(addedTask);
        int totalNumOfTasks = tasks.size();
        Ui.printAddedTask(addedTask.printTask(), totalNumOfTasks);
        Storage.saveData(tasks);
    }
}
