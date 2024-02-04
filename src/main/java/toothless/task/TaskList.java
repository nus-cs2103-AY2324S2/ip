package toothless.task;

import toothless.exception.ToothlessException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    private static final DateTimeFormatter DATETIME_PARSE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> taskArrayList) {
        this.tasks = taskArrayList;
    }
    
    public int getSize() {
        return tasks.size();
    }
    
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    
    public void markTask(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        printMarkedTask(task);
    }

    public void unmarkTask(int index) {
        Task task = tasks.get(index - 1);
        task.unmarkAsDone();
        printUnmarkedTask(task);
    }

    public void deleteTask(int index) {
        Task deletedTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        printDeletedTask(deletedTask);
    }

    public void addToDoToList(String taskDescription) {
        Task newTask = new ToDo(taskDescription);
        tasks.add(newTask);
        printNewTask(newTask);
    }

    public void addDeadlineToList(String taskDescription, String by) throws ToothlessException {
        try {
            LocalDateTime deadlineBy = LocalDateTime.parse(by, DATETIME_PARSE_FORMATTER);
            Task newTask = new Deadline(taskDescription, deadlineBy);
            tasks.add(newTask);
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
            tasks.add(newTask);
            printNewTask(newTask);
        } catch (DateTimeParseException e) {
            throw new ToothlessException("Sorry, /from and /to field datetime should use the following format: " +
                    "[yyyy-mm-dd hh:mm].");
        }
        
    }

    public void printNewTask(Task newTask) {
        String message =
                String.format("\tGot it. I've added this task:\n\t\t%s\n\tNya-ow you have %d tasks in the list.",
                        newTask, tasks.size());
        System.out.println(message);
    }

    public void printDeletedTask(Task deletedTask) {
        String message =
                String.format("\tNoted. I've remeowved this task:\n\t\t%s\n\tNya-ow you have %d tasks in the list.",
                        deletedTask, tasks.size());
        System.out.println(message);
    }
    
    public void printMarkedTask(Task markedTask) {
        System.out.println("\tAmeowzing! I've marked this task as done:\n\t" + markedTask);
    }

    public void printUnmarkedTask(Task unmarkedTask) {
        System.out.println("\tOK, I've marked this task as not done yet:\n\t" + unmarkedTask);
    }

    public void printList() {
        if (this.getSize() == 0) {
            System.out.print("\tOops! Looks like you haven't added any tasks yet!");
        }
        
        String listString = "";
        for (int i = 1; i <= tasks.size(); i++) {
            listString += "\t";
            listString += i + ". " + tasks.get(i - 1);
            if (i < tasks.size()) {
                listString += "\n";
            }
        }
        System.out.println(listString);
    }

}
