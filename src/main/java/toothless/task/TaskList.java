package toothless.task;

import toothless.exception.ToothlessException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    private static final DateTimeFormatter DATETIME_PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskList = taskArrayList;
    }
    
    public int size() {
        return taskList.size();
    }
    
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
    
    public void markTask(int index) {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        printMarkedTask(task);
    }

    public void unmarkTask(int index) {
        Task task = taskList.get(index - 1);
        task.unmarkAsDone();
        printUnmarkedTask(task);
    }

    public void deleteTask(int index) {
        Task deletedTask = taskList.get(index - 1);
        taskList.remove(index - 1);
        printDeletedTask(deletedTask);
    }

    public void addToDoToList(String taskDescription) {
        Task newTask = new ToDo(taskDescription);
        taskList.add(newTask);
        printNewTask(newTask);
    }

    public void addDeadlineToList(String taskDescription, String by) throws ToothlessException {
        try {
            LocalDateTime deadlineBy = LocalDateTime.parse(by, DATETIME_PARSE_FORMATTER);
            Task newTask = new Deadline(taskDescription, deadlineBy);
            taskList.add(newTask);
            printNewTask(newTask);
        } catch (DateTimeParseException e) {
            throw new ToothlessException("Sorry, /by field datetime should use the following format: " +
                    "[yyyy-mm-dd hh:mm].");
        }
    }

    public void addEventToList(String taskDescription, String from, String to) throws ToothlessException {
        try {
            LocalDateTime eventFrom = LocalDateTime.parse(from, DATETIME_PARSE_FORMATTER);
            LocalDateTime eventTo = LocalDateTime.parse(to, DATETIME_PARSE_FORMATTER);
            Task newTask = new Event(taskDescription, eventFrom, eventTo);
            taskList.add(newTask);
            printNewTask(newTask);
        } catch (DateTimeParseException e) {
            throw new ToothlessException("Sorry, /from and /to field datetime should use the following format: " +
                    "[yyyy-mm-dd hh:mm].");
        }
        
    }

    public void printNewTask(Task newTask) {
        String message =
                String.format("\tGot it. I've added this task:\n\t\t%s\n\tNya-ow you have %d tasks in the list.",
                        newTask, taskList.size());
        System.out.println(message);
    }

    public void printDeletedTask(Task deletedTask) {
        String message =
                String.format("\tNoted. I've remeowved this task:\n\t\t%s\n\tNya-ow you have %d tasks in the list.",
                        deletedTask, taskList.size());
        System.out.println(message);
    }
    
    public void printMarkedTask(Task markedTask) {
        System.out.println("\tAmeowzing! I've marked this task as done:\n\t" + markedTask);
    }

    public void printUnmarkedTask(Task unmarkedTask) {
        System.out.println("\tOK, I've marked this task as not done yet:\n\t" + unmarkedTask);
    }

    public void printList() {
        if (this.size() == 0) {
            System.out.print("\tOops! Looks like you haven't added any tasks yet!");
        }
        
        String listString = "";
        for (int i = 1; i <= taskList.size(); i++) {
            listString += "\t";
            listString += i + ". " + taskList.get(i - 1);
            if (i < taskList.size()) {
                listString += "\n";
            }
        }
        System.out.println(listString);
    }

}
