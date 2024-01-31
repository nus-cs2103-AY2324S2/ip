package solaire.tasklist;

import java.util.ArrayList;

import solaire.data.exception.SolaireException;
import solaire.data.task.Task;
import solaire.parser.Parser;

/**
 * Represents a list of Tasks.
 */
public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    private void lineBreak() {
        System.out.print("--------------------------------------------------\n");
    }

    /**
     * Parses and adds a task to the current list if input corresponds to task creation.
     *
     * @param input a user command input as String.
     */
    public void processTaskCommand(String input) {
        try {
            addToList(Parser.parseTaskInput(input));
        } catch (SolaireException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addToList(Task task) {
        if (task != null) {
            taskList.add(task);
            System.out.println("Added " + task + " to your list");
            lineBreak();
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Removes a specified task from the list of Tasks.
     *
     * @param input user input command in the format "delete (index)".
     */
    public void processRemoveFromList(String input) {
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
                System.out.println("Removed" + taskToDelete + " from your list");
            }
        } catch (SolaireException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Prints the current list of tasks.
     */
    public void showList() {
        System.out.print("Your list is as follows:\n " + "-------------------\n");
        for (Task item : taskList) {
            System.out.println(taskList.indexOf(item) + 1 + ". " + item.toString());
        }
    }

    /**
     * Mark a specified task as done.
     *
     * @param id 1-indexed integer identifier of the task as shown in the UI.
     */
    public void markDone(int id) {
        for (Task item : taskList) {
            if (item.getId() == id) {
                item.markAsDone();
                System.out.print("Marked item number: " + item.getId() + "\n");
                return;
            }
        }

        System.out.print("Couldn't find task associated with given id\n");
    }
    
    /**
     * Mark a specified task as "not done".
     *
     * @param id 1-indexed integer identifier of the task as shown in the UI.
     */
    public void unmarkDone(int id) {
        for (Task item : taskList) {
            if (item.getId() == id) {
                item.unmarkDone();
                System.out.print("Unmarked  item number: " + item.getId() + "\n");
                return;
            }
        }

        System.out.print("Couldn't find task associated with given id\n");
    }

    public void findTask(String prompt) {
        prompt = prompt.trim();
        if (prompt.equals("")) {
            System.out.println("Please insert a non-blank prompt to filter with.\n" + "-------------------\n");
            return;
        }
        System.out.print("Here are the matching tasks in your list:\n " + "-------------------\n");
        int filteredIndex = 1;
        for (Task task : taskList) {
            if (task.getDescription().contains(prompt)) {
                System.out.println(filteredIndex + ". " + task.toString());
                filteredIndex++;
            }
        }
    }

}
