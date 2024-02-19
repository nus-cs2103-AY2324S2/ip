package yue.Command;

import yue.Storage;
import yue.Tasks.Task;
import yue.Tasks.TaskList;
import yue.YueException;

import java.util.List;
import java.util.ArrayList;

public class DetectCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage) throws YueException {
        List<Task> taskList = tasks.getAllTasks();
        String res = "";

        // List to store duplicate tasks
        List<Task> duplicateTasks = new ArrayList<>();

        // Iterate through the task list
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            String currentDescription = currentTask.getTask();

            // Compare the current task's description with descriptions of other tasks
            for (int j = i + 1; j < taskList.size(); j++) {
                Task otherTask = taskList.get(j);
                String otherDescription = otherTask.getTask();

                // Compare descriptions character by character
                boolean isDuplicate = compareDescriptions(currentDescription, otherDescription);

                if (isDuplicate) {
                    duplicateTasks.add(currentTask);
                }
            }
        }

        // Remove duplicate tasks from the task list
        for (Task duplicateTask : duplicateTasks) {
            tasks.removeTask(duplicateTask);
        }

        // Save the updated task list to storage
        storage.save(tasks.getAllTasks());

        // Build the response string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < duplicateTasks.size(); i++) {
            String list = "    " + (i + 1) + ". " + duplicateTasks.get(i);
            sb.append(list).append(System.lineSeparator());
        }

        if (duplicateTasks.size() == 0) {
            res = "Great! There is no duplicates in the task list.";
        } else {
            res = "Duplicates removed successfully:\n" + sb.toString() + "\n";
        }

        return res;
    }


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