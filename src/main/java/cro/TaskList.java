package cro;

import tasks.*;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> taskList = new ArrayList<>();
    static boolean isLoading = false;
    public TaskList(List<Task> loadedTasks) throws CroException {
        taskList = loadedTasks;
    }

    public TaskList() {
    }


    public void addToTaskList(Task newTask) {
        taskList.add(newTask);
        if (!isLoading) {
            System.out.println("-----------------------------------");
            System.out.println("added: " + newTask);
            System.out.println("-----------------------------------");
        }
    }
    public void addToDo(List<String> splitStr) throws CroException {
        ToDo newToDo = new ToDo(splitStr);
        addToTaskList(newToDo);
    }

    public void addDeadline(List<String> splitStr) throws CroException {

        Deadline newDeadline = new Deadline(splitStr);
        addToTaskList(newDeadline);
    }

    public void addEvent(List<String> splitStr) throws CroException {
        Event newEvent = new Event(splitStr);
        addToTaskList(newEvent);
    }

    public void displayTasks() {
        for (int i = 0; i < taskList.size(); i++) {
            String output = String.format("%d. %s", i+1, taskList.get(i));
            System.out.println(output);
        }
        System.out.printf("Now you have %d tasks in the list%n", taskList.size());
    }

    public void markTaskAsDone(List<String> splitStr) throws CroException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(0));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            taskList.get(taskNo - 1).markAsDone();
            System.out.println("-----------------------------------");
            System.out.println("Nice! I've marked this task as done!");
            System.out.println(taskList.get(taskNo - 1));
            System.out.println("-----------------------------------");
        }
    }

    public void markTaskAsUndone(List<String> splitStr) throws CroException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(0));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            taskList.get(taskNo - 1).markAsUndone();
            System.out.println("-----------------------------------");
            System.out.println("OK, I've marked this task as not done yet.");
            System.out.println(taskList.get(taskNo - 1));
            System.out.println("-----------------------------------");
        }
    }

    public void deleteEvent(List<String> splitStr) throws CroException {
        int taskNo;
        try {
            taskNo = Integer.parseInt(splitStr.get(0));
        } catch (Exception e) {
            throw new CroException("index must be a number");
        }
        if (taskNo > taskList.size()) {
            throw new CroException("Task not found!");
        } else {
            Task removedTask = taskList.remove(taskNo - 1);
            System.out.println("-----------------------------------");
            System.out.println("OK, I've removed this task.");
            System.out.println(removedTask);
            System.out.printf("Now you have %d tasks in the list%n", taskList.size());
            System.out.println("-----------------------------------");
        }
    }
}
