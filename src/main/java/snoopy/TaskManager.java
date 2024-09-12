package snoopy;

import exceptions.BadDateException;
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


    /**
     * Checks if the delete command is valid
     * @param inputArguments the array of input arguments
     * @return true if the delete command is valid, false otherwise
     */
    public static boolean isValidDeleteCommand(String[] inputArguments) {
        return inputArguments.length == 2;
    }

    public static boolean isValidDeadlineCommand(String[] inputArguments) {
        try {
            String arguments[] = inputArguments[1].split(" /by ");
            String description = arguments[0];
            String date = arguments[1];
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the event command is valid
     * @param inputArguments the array of input arguments
     * @return true if the event command is valid, false otherwise
     */
    private static boolean isValidEventCommand(String[] inputArguments) {
        try {
            // attempt extraction of parameters
            String getDesc[] = inputArguments[1].split(" /from ");
            String desc = getDesc[0];
            String getDates[] = getDesc[1].split(" /to ");
            String getDateAndTag[] = getDates[1].split(" /tag ");
            String from = getDates[0];
            String to = getDateAndTag[0];
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Handles the delete command and removes the task from the list
     * @param inputArguments the array of input arguments
     * @param UserInput the user input
     * @param todos the list of tasks
     * @param isVerbose the verbosity of the output
     * @param storage the storage object
     * @return the string output
     */
    public static String processDelete(String[] inputArguments, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        if (!isValidDeleteCommand(inputArguments)) {
            return ("inputArguments:" + Arrays.toString(inputArguments) + "\n Nuh uh! Which task to delete? \nMake sure to add the task number!");
        }
        Integer i = Integer.valueOf(inputArguments[1]);
        Task task;
        try {
            task = todos.get(i - 1);
            todos.remove(i - 1);
        } catch (Exception e) {
           Exception e1 = new TaskNotExistException(Integer.toString(i));
           return e1.getMessage();
        }
        if (isVerbose) {
            storage.updateRecords(todos);
        }
        return ("Okay! I've fed this task to Woodstock, bye bye!:" + "\n" + task.toString() + "\n" + "Now you have " + todos.size() + " tasks in the list.");
    }

    /**
     * Handles the event command and interpreting details like dates and tags
     * @param inputArguments the inputArgumentsay of strings
     * @param UserInput the user input
     * @param todos the list of tasks
     * @param isVerbose the verbosity of the output
     * @param storage the storage object
     * @return the string output
     */
    public static String processEvent(String[] inputArguments, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        if (!isValidEventCommand(inputArguments)) {
            throw new DukeException(" Insufficient parameters! \nMake sure to add a from and to date after the description with /from and /to too!");
        }
        // extraction of parameters
        String getDesc[] = inputArguments[1].split(" /from ");
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
            storage.updateRecords(todos);
        }

        return ("Wow hardworker! Added: " + "\n" + event.toString() + "\n" + ("Now you have " + todos.size() + " tasks in the list."));
    }

    /**
     * Processes the deadline command
     * @param inputArguments the inputArgumentsay of strings
     * @param UserInput the user input
     * @param todos the list of tasks
     * @param isVerbose the verbosity of the output
     * @param storage the storage object
     * @return the string output
     */
    public static String processDeadline(String[] inputArguments, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        if (!isValidDeadlineCommand(inputArguments)) {
            throw new DukeException(" Nuh uh! Insufificent parameters!\nMake sure to add a deadline after the description with /by too!");
        }
        String arguments[] = inputArguments[1].split(" /by ");
        String description = arguments[0];
        String byAndTag[] = arguments[1].split(" /tag ");

        Deadline deadline;
        try {
            String by = byAndTag[0];
            if (byAndTag.length > 1) {
                String tag = byAndTag[1];
                deadline = new Deadline(description, by, tag);
            } else {
                deadline = new Deadline(description, by);
            }
        } catch (Exception e) {
            return "Bad date! Please use the format YYYY-MM-DD or YYYY/MM/DD or DD-MM-YYYY or DD/MM/YYYY e.g. 2024-12-14";
        }
        todos.add(deadline);
        if (isVerbose) {
            storage.updateRecords(todos);
        }
        return ("Ah deadlines. Added this task:" + "\n" + deadline.toString() + "\n" + ("Now you have " + todos.size() + " tasks in the list."));
    }

    /**
     * Processes the todo command
     * @param inputArguments the inputArgumentsay of strings
     * @param UserInput the user input
     * @param todos the list of tasks
     * @param isVerbose the verbosity of the output
     * @param storage the storage object
     * @return the string output
     */
    public static String processTodo(String[] inputArguments, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        if (inputArguments.length == 1) {
            throw new DukeException(" Nuh uh! The description of a todo cannot be empty.");
        }
        String arguments[] = inputArguments[1].split(" /tag ");
        Todo todo;
        if (arguments.length > 1) { // if there are tags
            todo = new Todo(arguments[0], arguments[1]);
        } else {
            todo = new Todo(arguments[0]);
        }
        todos.add(todo);
        if (isVerbose) {
            storage.updateRecords(todos);
        }
        return ("Ooo happening! Added this task:\n" + todo.toString() + "\n" + "Now you have " + todos.size() + " tasks in the list.");
    }

    /**
     * Processes the mark command
     * @param inputArguments the inputArgumentsay of strings
     * @param UserInput the user input
     * @param todos the list of tasks
     * @param isVerbose the verbosity of the output
     * @param storage the storage object
     * @return the string output
     */
    public static String processMark(String[] inputArguments, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        // mark task as done
        Integer index = Integer.valueOf(inputArguments[1]) - 1;
        Task currTask = todos.get(index);
        currTask.markAsDone();
        if (isVerbose) {
            storage.updateRecords(todos);
        }
        return (" Great job! I've marked this as done:\n" + " " + currTask.toString());
    }

    /**
     * Processes the list command
     * @param inputArguments the arguments of strings
     * @param UserInput the user input
     * @param todos the list of tasks
     * @param isVerbose the verbosity of the output
     * @return the string output
     */
    public static String processList(String[] inputArguments, String UserInput, TaskList todos, Boolean isVerbose) {
        String tasksString = "";
        for (int i = 0; i < todos.size(); i++) {
            Task currTask = todos.get(i);
            if (isVerbose) {
                tasksString += ((i + 1) + ". " + currTask.toString() + "\n");
            }
        }
        return (" Here are the tasks in your list:\n" + tasksString);
    }

    public static String processUnmark(String[] inputArguments, String UserInput, TaskList todos, Boolean isVerbose, Storage storage) {
        Integer index = Integer.valueOf(inputArguments[1]) - 1;
        Task currTask = todos.get(index);
        // mark task as undone
        index = Integer.valueOf(inputArguments[1]) - 1;
        currTask = todos.get(index);
        currTask.markAsUndone();

        if (isVerbose) {
            storage.updateRecords(todos);
        }
        return (" OK, I've marked this task as not done yet:\n" + " " + currTask.toString());
    }
    public static String processFind(String[] inputArguments, String UserInput, TaskList todos, Boolean isVerbose) {
        TaskList matchingTasks = new TaskList();
        String query = inputArguments[1];
        String matchingTasksString = "";
        for (int i = 0; i < todos.size(); i++) {
            Task currTask = todos.get(i);
            if (currTask.toString().contains(query)) {
                matchingTasks.add(currTask);
            }
        }
        if (matchingTasks.size() > 0) {
            for (int i = 0; i < matchingTasks.size(); i++) {
                matchingTasksString += ((i + 1) + ". " + matchingTasks.get(i).toString() + "\n");
            }
        } else {
            return (" Sorry no tasks found matching that word :<");
        }
        return (" Here are the matching tasks:\n" + matchingTasksString);
    }
}
