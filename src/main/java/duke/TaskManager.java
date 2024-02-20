package duke;

import exceptions.DukeException;
import exceptions.TaskNotExistException;
import model.Deadline;
import model.Event;
import model.Task;
import model.Todo;
import service.TaskList;
import service.Storage;

import java.util.Arrays;

public class TaskManager {


    public static boolean isValidDelete(String[] arr) {
        if (arr.length <= 1) {
            return false;
        }
        return true;
    }
    public static String processDelete(String[] arr, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        if (!isValidDelete(arr)) {
            return ("arr:" + Arrays.toString(arr) + "\n Nuh uh! Which task to delete? \nMake sure to add the task number!");
        }
        Integer i = Integer.valueOf(arr[1]);
        Task task;
        try {
            task = todos.get(i - 1);
            todos.remove(i - 1);
        } catch (Exception e) {
           Exception e1 = new TaskNotExistException(Integer.toString(i));
           return e1.getMessage();
        }
        if (isVerbose) {
            System.out.println("Okay! I've fed this task to Woodstock, bye bye!:");
            System.out.println(task.toString());
            System.out.println("Now you have " + todos.size() + " tasks in the list.");
            storage.updateRecords(todos);
        }
        System.out.println("Reached here");
        return ("Okay! I've fed this task to Woodstock, bye bye!:" + "\n" + task.toString() + "\n" + "Now you have " + todos.size() + " tasks in the list.");
    }
    public static String processEvent(String[] arr, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        if (arr.length == 4) {
            throw new DukeException(" Nuh uh! The description of an event cannot be empty.\nMake sure to add a from and to date after the description with /from and /to too!");
        }
        if (isVerbose) {
            System.out.println("Got it. Added this task:");
        }
        // extraction of parameters
        String getDesc[] = arr[1].split(" /from ");
        String desc = getDesc[0];
        String getDates[] = getDesc[1].split(" /to ");
        String getDateAndTag[] = getDates[1].split(" /tag ");
        String from = getDates[0];
        String to = getDateAndTag[0];

        String tag;
        Event event;
        try {
            if (getDateAndTag.length > 1) {
                tag = getDateAndTag[1];
                event = new Event(desc, from, to, tag);
                todos.add(event);
            } else {
                event = new Event(desc, from, to);
                todos.add(event);
            }
        } catch (Exception e) {
            return e.getMessage();
        }

        if (isVerbose) {
            System.out.println(event.toString());
            System.out.println("Now you have " + todos.size() + " tasks in the list.");
            storage.updateRecords(todos);
        }

        return ("Wow hardworker! Added: " + "\n" + event.toString() + "\n" + ("Now you have " + todos.size() + " tasks in the list."));
    }

    public static String processDeadline(String[] arr, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        if (arr.length == 1) {
            throw new DukeException(" Nuh uh! The description of a deadline cannot be empty.\nMake sure to add a deadline after the description with /by too!");
        }
        if (isVerbose) {
            System.out.println("Got it. Added this task:");
        }
        String arguments[] = arr[1].split(" /by ");
        String description = arguments[0];
        String byAndTag[] = arguments[1].split(" /tag ");
        String by = byAndTag[0];
        String tag = byAndTag[1];

        Deadline deadline;
        try {
            if (byAndTag.length > 1) {
                deadline = new Deadline(description, by, tag);
            } else {
                deadline = new Deadline(description, by);
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        todos.add(deadline);
        if (isVerbose) {
            System.out.println(deadline.toString());
            System.out.println("Now you have " + todos.size() + " tasks in the list.");
            storage.updateRecords(todos);
        }
        return ("Ah deadlines. Added this task:" + "\n" + deadline.toString() + "\n" + ("Now you have " + todos.size() + " tasks in the list."));
    }
    public static String processTodo(String[] arr, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        if (arr.length == 1) {
            throw new DukeException(" Nuh uh! The description of a todo cannot be empty.");
        }
        if (isVerbose) {
            System.out.println("Got it. Added this task:");
        }
        String arguments[] = arr[1].split(" /tag ");
        System.out.println("arguments:" + Arrays.toString(arguments));
        Todo todo;
        if (arguments.length > 1) { // if there are tags
            todo = new Todo(arguments[0], arguments[1]);
        } else {
            todo = new Todo(arguments[0]);
        }
        todos.add(todo);
        if (isVerbose) {
            System.out.println(todo.toString());
            System.out.println("Now you have " + todos.size() + " tasks in the list.");
            storage.updateRecords(todos);
        }
        return ("Ooo happening! Added this task:\n" + todo.toString() + "\n" + "Now you have " + todos.size() + " tasks in the list.");
    }
    public static String processMark(String[] arr, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        // mark task as done
        Integer index = Integer.valueOf(arr[1]) - 1;
        Task currTask = todos.get(index);
        currTask.markAsDone();
        if (isVerbose) {
            System.out.print(" Mark task as done:\n");
            System.out.println(" " + currTask.toString());
            storage.updateRecords(todos);
        }
        return (" Great job! I've marked this as done:\n" + " " + currTask.toString());
    }
    public static String processList(String[] arr, String UserInput, TaskList todos, Boolean isVerbose) {
        if (isVerbose) {
            System.out.println(" Here are the tasks in your list:");
        }
        String tasksString = "";
        for (int i = 0; i < todos.size(); i++) {
            Task currTask = todos.get(i);
            if (isVerbose) {
                System.out.println((i + 1) + ". " + currTask.toString());
                tasksString += ((i + 1) + ". " + currTask.toString() + "\n");
            }
        }
        return (" Here are the tasks in your list:\n" + tasksString);
    }

    public static String processUnmark(String[] arr, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        Integer index = Integer.valueOf(arr[1]) - 1;
        Task currTask = todos.get(index);
        // mark task as undone
        index = Integer.valueOf(arr[1]) - 1;
        if (isVerbose) {
            System.out.print(" OK, I've marked this task as not done yet:\n");
        }

        currTask = todos.get(index);
        currTask.markAsUndone();

        if (isVerbose) {
            System.out.println(" " + currTask.toString());
            storage.updateRecords(todos);
        }
        return (" OK, I've marked this task as not done yet:\n" + " " + currTask.toString());
    }
    public static String processFind(String[] arr, String UserInput, TaskList todos, Boolean isVerbose) {
        TaskList matchingTasks = new TaskList();
        String query = arr[1];
        String matchingTasksString = "";
        for (int i = 0; i < todos.size(); i++) {
            Task currTask = todos.get(i);
            if (currTask.toString().contains(query)) {
                matchingTasks.add(currTask);
            }
        }
        if (matchingTasks.size() > 0) {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i).toString());
                matchingTasksString += ((i + 1) + ". " + matchingTasks.get(i).toString() + "\n");
            }
        } else {
            System.out.println(" Sorry no tasks found matching that word :<");
            return (" Sorry no tasks found matching that word :<");
        }
        return (" Here are the matching tasks:\n" + matchingTasksString);
    }
}
