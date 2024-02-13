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
        throw new AssertionError("Constructor is not allowed");
        // assert false : "Execution should never reach this point!";
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
            int splitWhiteSpace = 2;
            String[] parts = input.split(" ", splitWhiteSpace);

            int getDeleteIdx = 1;
            int pos = Integer.parseInt(parts[getDeleteIdx]);

            if (pos > taskList.size() || pos <= 0) {
                throw new TaylorException("Invalid task number");
            }

            int idx = pos - 1;
            Task taskRemoved = taskList.get(idx);
            response.append("Noted. I've removed this tasks:\n");
            response.append(taskRemoved).append("\n");
            taskList.remove(idx);
            response.append("Now you have ").append(taskList.size()).append(" tasks in the list.").append("\n");

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException err) {
            throw new TaylorException("Please include index of task to be removed");
        }
        return response.toString();
    }
}
