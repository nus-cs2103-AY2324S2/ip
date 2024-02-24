package eve;
import java.util.ArrayList;

import eve.exceptions.EveExceptions;
import eve.tasks.Deadline;
import eve.tasks.Event;
import eve.tasks.Task;
import eve.tasks.Todo;
/**
 * TaskList class is used to handle the commands that are input by the user
 * It contains the methods to handle the commands
 */
public class TaskList {
    /**
     * This method is used to list out the tasks in the list
     * @param list is the list of tasks
     */
    public static String commandList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            return "Your task list is empty !!";
        }

        StringBuilder response = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < list.size(); i++) {
            response.append(i + 1).append(".").append(list.get(i)).append("\n");
        }
        return response.toString().trim();
    }
        


    /**
     * This method is used to mark a task as done
     * @param tempyArr is the array of the input command
     * @param list is the list of tasks
     */
    public static String commandMark(String[] tempyArr, ArrayList<Task> list) {
        assert list.size() > 0 : "Task list is empty";
        int index = Integer.parseInt(tempyArr[1]) - 1;
        Task temp = list.get(index);
        temp.markAsDone();

        StringBuilder response = new StringBuilder("Nice! I've marked this task as done:  \n");
        
        response.append(temp.toString()).append("\n");
        
        return response.toString().trim();
    }

    /**
     * This method is used to mark a task as not done
     * @param tempyArr is the array of the input command
     * @param list is the list of tasks
     */

    public static String commandUnMark(String[] tempyArr, ArrayList<Task> list) {
        assert list.size() > 0 : "Task list is empty";
        int index = Integer.parseInt(tempyArr[1]) - 1;
        Task temp = list.get(index);
        temp.markAsNotDone();

        StringBuilder response = new StringBuilder("Nice! I've marked this task as not done yet:  \n");
        
        response.append(temp.toString()).append("\n");

        return response.toString().trim();
    }
    /**
     * This method is used to delete a task
     * @param tempyArr is the array of the input command
     * @param list is the list of tasks
     */
    public static String commandDelete(String[] tempyArr, ArrayList<Task> list) {
        assert list.size() > 0 : "Task list is empty";
        int index = Integer.parseInt(tempyArr[1]) - 1;
        Task temp = list.get(index);
        list.remove(temp);

        StringBuilder response = new StringBuilder("Noted. I've removed this task:   \n");
        
        response.append(temp.toString()).append("\n").append("Now you have " + list.size() + " tasks in the list.");

        return response.toString().trim();

    }

    /**
     * This method is used to add a todo task
     * @param tempyArr is the array of the input command
     * @param list is the list of tasks
     * @throws EveExceptions
     */

    public static String commandTodo(String[] tempyArr, ArrayList<Task> list) {
        if (tempyArr.length < 2) {
            return "This todo can't be empty";
        }
        String description = tempyArr[1];
        Task t = new Todo(description);
        list.add(t);

        StringBuilder response = new StringBuilder("Got it. I've added this task   \n");
        
        response.append(t.toString()).append("\n").append("Now you have " + list.size() + " tasks in the list.");

        return response.toString().trim();
    }

    /**
     * This method is used to add a deadline task
     * @param tempyArr is the array of the input command
     * @param list is the list of tasks
     */
    public static String commandDeadline(String[] tempyArr, ArrayList<Task> list) {
        String description = tempyArr[1];
        String[] arrTemp = description.split(" /by ");
        description = arrTemp[0];
        String by = arrTemp[1];
        Task t = new Deadline(description, by);
        list.add(t);

        StringBuilder response = new StringBuilder("Got it. I've added this task   \n");
        
        response.append(t.toString()).append("\n").append("Now you have " + list.size() + " tasks in the list.");

        return response.toString().trim();
    }

    /**
     * This method is used to add an event task
     * @param tempyArr is the array of the input command
     * @param list is the list of tasks
     */
    public static String commandEvent(String[] tempyArr, ArrayList<Task> list) {
        String description = tempyArr[1];
        String[] arrTemp = description.split(" /from ");
        description = arrTemp[0];
        String[] dateArr = arrTemp[1].split(" /to ");
        String startDate = dateArr[0];
        String endDate = dateArr[1];
        Task t = new Event(description, startDate, endDate);
        list.add(t);

        StringBuilder response = new StringBuilder("Got it. I've added this task   \n");
        
        response.append(t.toString()).append("\n").append("Now you have " + list.size() + " tasks in the list.");

        return response.toString().trim();
    }

    public static String commandFind(String[] tempyArr, ArrayList<Task> list) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        String taskToFind = tempyArr[1];
        
        for (Task task : list) {
            if (task.getTask().contains(taskToFind)) {
                matchingTasks.add(task);
            }
        }

        StringBuilder response = new StringBuilder();
        
        if (matchingTasks.isEmpty()) {
            response.append("No matching tasks found");
        } else {
            response.append("Here are the matching tasks in your list: \n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
            }
        }
        return response.toString().trim();
    }
}
