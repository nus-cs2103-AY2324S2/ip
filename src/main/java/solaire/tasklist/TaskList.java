package solaire.tasklist;

import java.util.ArrayList;

import solaire.data.exception.SolaireException;
import solaire.data.task.Deadline;
import solaire.data.task.Task;
import solaire.parser.Parser;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses and adds a task to the current list if input corresponds to task creation.
     *
     * @param input a user command input as String.
     */
    public String processTaskCommand(String input) {
        try {
            return addToList(Parser.parseTaskInput(input));
        } catch (SolaireException e) {
            return e.getMessage();
        }
    }

    private String addToList(Task task) {
        String output = "";
        if (task != null) {
            taskList.add(task);
            output = "Added " + task + " to your list";
        } else {
            output = "Task not added to list";
        }
        return output;

    }

    /**
     * Returns the current list of tasks.
     *
     * @return an <code>ArrayList</code> of <code>Task</code> objects.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Removes a specified task from the list of Tasks.
     *
     * @param input user input command in the format "delete (index)".
     * @return a String message indicating the task has been removed from the list.
     */
    public String processRemoveFromList(String input) {
        try {
            String[] inputCommand = input.split(" ", 2);
            if (inputCommand.length < 2) {
                throw new SolaireException("Please specify the ID of the task you wish to delete\n");
            } else {
                Integer targetTaskId = Integer.parseInt(inputCommand[1]);

                if (targetTaskId > this.taskList.size() || targetTaskId <= 0) {
                    throw new SolaireException("Task number " + targetTaskId + " does not exist");
                }
                Task taskToDelete = taskList.get(targetTaskId - 1);
                taskList.remove(taskToDelete);
                String output = "Removed" + taskToDelete + " from your list";
                return output;
            }
        } catch (SolaireException e) {
            String output = e.getMessage();
            return output;
        }
    }

    /**
     * Returns the String representation of the current list of tasks.
     *
     * @return a String representation of the current list of tasks.
     */
    public String showList() {
        String output = "Your list is as follows:\n " + "-------------------\n";
        for (Task item : taskList) {
            output += taskList.indexOf(item) + 1 + ". " + item.toString() + "\n";
        }
        return output;
    }

    /**
     * Marks a specified task as done.
     *
     * @param id 1-indexed integer identifier of the task as shown in the UI.
     * @return a String message indicating the task has been marked as done.
     */
    public String markDone(int id) {
        assert id > 0 : "Task ID should be a positive integer";
        assert id < taskList.size() : "Task ID should be less than the size of the task list";
        String output = "";
        for (Task item : taskList) {
            if (item.getId() == id) {
                item.markAsDone();
                output = "Marked item number: " + item.getId() + "\n";
                return output;
            }
        }
        output = "Couldn't find task associated with given id\n";
        return output;
    }

    /**
     * Marks a specified task as "not done".
     *
     * @param id 1-indexed integer identifier of the task as shown in the UI.
     * @return a String message indicating the task has been marked as "not done".
     */
    public String unmarkDone(int id) {
        String output = "";
        for (Task item : taskList) {
            if (item.getId() == id) {
                item.unmarkDone();
                output = "Unmarked  item number: " + item.getId() + "\n";
                return output;
            }
        }
        output = "Couldn't find task associated with given id\n";
        return output;
    }

    /**
     * Filters the current list of tasks based on a given prompt and returns the corresponding list.
     *
     * @param prompt a user input to match task descriptions against.
     * @return a String message containing the filtered list of tasks.
     */
    public String findTask(String prompt) {
        prompt = prompt.trim();
        String output = "";
        if (prompt.equals("")) {
            output = "Please insert a non-blank prompt to filter with.\n" + "-------------------\n";
            return output;
        }
        output = "Here are the matching tasks in your list:\n " + "-------------------\n";
        int filteredIndex = 1;
        for (Task task : taskList) {
            if (task.getDescription().contains(prompt)) {
                output += filteredIndex + ". " + task.toString() + "\n";
                filteredIndex++;
            }
        }
        return output;
    }

    /**
     * Reminds the user of upcoming deadlines and events.
     *
     * @param buffer an optional buffer to specify the number of days to look ahead.
     * @return a message containing the upcoming deadlines and events.
     */
    public String remindMe(String... buffer) {

        // If user specifies no particular buffer, default to 1 day
        int bufferDays = 1;
        if (buffer.length != 0) {
            bufferDays = Integer.parseInt(buffer[0]);
        }
        String output = "Here's what's due in " + bufferDays + " days:\n ";
        int reminderIndex = 1;
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.getDueStatus(bufferDays)) {
                    output += reminderIndex + ". " + deadlineTask.toString() + "\n";
                    reminderIndex++;
                }
            }
        }
        return output;
    }

}
