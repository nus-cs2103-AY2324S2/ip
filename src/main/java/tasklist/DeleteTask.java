package tasklist;

import java.util.List;

import exceptions.TaylorException;
import tasks.Task;

/**
 * To delete a task
 */
public class DeleteTask {
    /**
     * No constructor needed
     */
    private DeleteTask() {
        // throw new AssertionError("Constructor is not allowed");
        assert false : "Execution should never reach this point!";
    }

    /**
     * Execute Deleting tasks
     *
     * @param input    : User input
     * @param taskList
     * @throws TaylorException
     */
    public static String execDeleteTask(String input, List<Task> taskList) throws TaylorException {
        StringBuilder response = new StringBuilder();
        try {
            // Input will be 'delete x', where x is an int
            // Then split to get x
            String[] parts = input.split(" ", 2);
            int pos = Integer.parseInt(parts[1]);

            // If x is negative or x is less than total no. of Task, throw error
            if (pos > taskList.size() || pos <= 0) {
                throw new TaylorException("Invalid task number");
            }

            Task taskRemoved = taskList.get(pos - 1);
            response.append("Noted. I've removed this tasks:\n");
            response.append(taskRemoved).append("\n");
            taskList.remove(pos - 1);
            response.append("Now you have ").append(taskList.size()).append(" tasks in the list.").append("\n");

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException err) {
            throw new TaylorException("Please include index of task to be removed");
        }
        return response.toString();
    }
}
