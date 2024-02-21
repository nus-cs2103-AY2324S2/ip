package yue.command;

import yue.Storage;
import yue.tasks.Task;
import yue.tasks.TaskList;
import yue.YueException;

import java.util.List;
import java.util.ArrayList;

public class DetectCommand extends Command {

    /**
     * Executes the command to find and remove duplicate tasks from the task list.
     *
     * @param tasks   The list of tasks to check for duplicates.
     * @param storage The storage handler to save the updated task list.
     * @return A message indicating the outcome of the operation.
     * @throws YueException If an error occurs during the execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws YueException {
        List<Task> duplicateTasks = findAndRemoveDuplicates(tasks);
        storage.save(tasks.getAllTasks());
        return generateResultMessage(duplicateTasks);
    }

    /**
     * Finds and removes duplicate tasks from the given task list.
     *
     * @param tasks The TaskList object containing the tasks to check for duplicates.
     * @return A list of duplicate tasks that were removed.
     */
    private List<Task> findAndRemoveDuplicates(TaskList tasks) {
        List<Task> taskList = tasks.getAllTasks();
        List<Task> duplicateTasks = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            String currentDescription = currentTask.getTask();
            for (int j = i + 1; j < taskList.size(); j++) {
                Task otherTask = taskList.get(j);
                String otherDescription = otherTask.getTask();
                boolean isDuplicate = compareDescriptions(currentDescription, otherDescription);
                if (isDuplicate) {
                    duplicateTasks.add(currentTask);
                }
            }
        }
        for (Task duplicateTask : duplicateTasks) {
            tasks.removeTask(duplicateTask);
        }
        return duplicateTasks;
    }

    /**
     * Generates a result message based on the duplicate tasks found.
     *
     * @param duplicateTasks The list of duplicate tasks.
     * @return A message indicating the result of duplicate removal.
     */
    private String generateResultMessage(List<Task> duplicateTasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < duplicateTasks.size(); i++) {
            String list = "    " + (i + 1) + ". " + duplicateTasks.get(i);
            sb.append(list).append(System.lineSeparator());
        }
        if (duplicateTasks.isEmpty()) {
            return "Great! There are no duplicates in the task list.";
        } else {
            return "Duplicates removed successfully:\n" + sb.toString() + "\n";
        }
    }



    /**
     * Compares two task descriptions to determine if they are duplicates.
     *
     * @param desc1 The description of the first task.
     * @param desc2 The description of the second task.
     * @return True if the descriptions are identical, false otherwise.
     */
    private boolean compareDescriptions(String desc1, String desc2) {
        if (desc1.length() != desc2.length()) {
            return false;
        }
        for (int i = 0; i < desc1.length(); i++) {
            if (desc1.charAt(i) != desc2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return Always returns false, as this is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}