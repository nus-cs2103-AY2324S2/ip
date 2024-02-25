package duke.Command;
import duke.TaskList;


import duke.Task.Task;
import java.util.ArrayList;

import duke.DukeException;

public class FindCommand extends Command{

    
    public FindCommand() {
       
    }

    @Override
    public String execute(TaskList taskList, String command) throws DukeException {
        // Split the command string into parts using space as delimiter
        String[] parts = command.split(" ", 2); // Limit the split to 2 parts

        // Check if the command is properly formatted
        if (parts.length != 2 || !parts[0].equalsIgnoreCase("find")) {
            throw new DukeException("Invalid find command format. Please use: find <description>");
        }

        // Extract the description from the second part of the command
        String description = parts[1].trim();

        // Perform the find operation using the extracted description
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getDescription().contains(description)) {
                foundTasks.add(task);
            }
        }

        // Build a string containing information about the found tasks
        StringBuilder result = new StringBuilder();
        if (!foundTasks.isEmpty()) {
            result.append("Matching tasks:\n");
            for (Task task : foundTasks) {
                result.append(task.toString()).append("\n");
            }
        } else {
            result.append("No matching tasks found.");
        }

        return result.toString();
    }
    
}
