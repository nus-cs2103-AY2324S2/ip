package TaskStorage;

import Task.Task;

import NicoleExceptions.NicoleException;

import UserRequests.Request;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskList {
    public static final List<Task> taskList = new ArrayList<>();
    private final Storage storage;
    public TaskList() throws NicoleException {
        storage = new Storage();
    }
    private void crudChecker(int taskNumber) throws NicoleException {
        if (taskNumber <= 0 || taskNumber > TaskList.taskList.size()) {
            throw new NicoleException("Huh? That's not a valid item number :')");
        }
    }
    public void unmarkTask(int taskNumber) throws NicoleException {
        this.crudChecker(taskNumber);
        System.out.println(TaskList.taskList.get(taskNumber - 1).markUndone());
        this.storage.saveTasksToFile();
    }

    public void markTask(int taskNumber) throws NicoleException {
        this.crudChecker(taskNumber);
        System.out.println(TaskList.taskList.get(taskNumber - 1).markDone());
        this.storage.saveTasksToFile();
    }

    public void deleteTask(int taskNumber) throws NicoleException {
        this.crudChecker(taskNumber);
        TaskList.taskList.remove(taskNumber - 1);
        System.out.println("Nicole: Phew...deleted  :>");
        this.storage.saveTasksToFile();
    }

    public void addTask(Task newTask) throws NicoleException {
        TaskList.taskList.add(newTask);
        try {
            FileWriter taskFileWriter = new FileWriter("./data/tasks.txt", true);
            taskFileWriter.write(newTask.toString() + "\n");
            taskFileWriter.close();
        } catch (IOException e) {
            throw new NicoleException("I couldn't save the task >< try again plss");
        }
        System.out.println("Nicole: Oki I added " + "\"" + newTask.toString().substring(7) + "\"");
    }
    public void listTasks() {
        if (TaskList.taskList.size() == 0) {
            System.out.println("Nicole: No tasks yet. Let's make some moves BD");
            return;
        }
        System.out.println("Nicole: Here's the tasks I saved so far,");
        if (Request.priorityTasking) {
            Comparator<Task> sorter = (task1, task2) -> {
                if (task1.getDate().isBefore(task2.getDate())) {
                    return -1;
                } else if (task1.getDate().isEqual(task2.getDate())) {
                    return 0;
                } else {
                    return 1;
                }
            };
            TaskList.taskList.sort(sorter);
        }
        for (int i = 0; i < TaskList.taskList.size(); i++) {
            System.out.println((i + 1) + ". " + TaskList.taskList.get(i));
        }
    }
}
