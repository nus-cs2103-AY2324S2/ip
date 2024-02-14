package duke;

/**
 * Return appropriate Duke response for each user input.
 */
public class CommandHandler {
    /**
     * This method handles the logic for chatting with the user
     * @param taskList the task list that stores all the tasks created by the user
     */
    public static String chat(String input, TaskList taskList) throws RiriException {

        if (input.matches("bye")) {
            return "Press the cross on your console";
        } else if (input.toLowerCase().matches("list")) {
            // Print list
            return taskList.returnList();
        } else if (input.toLowerCase().matches("\\bmark\\b.*")) {
            // Mark task as done
            String[] words = input.split("\\s+");
            return taskList.mark(Integer.parseInt(words[1]));
        } else if (input.toLowerCase().matches("\\bunmark\\b.*")) {
            // Mark the task as undone
            String[] words = input.split("\\s+");
            return taskList.unmark(Integer.parseInt(words[1]));
        } else if (input.toLowerCase().matches("\\bdeadline\\b.*")) {
            // Add deadline task to task list
            String[] words = input.split("/by");
            String date = words[1].trim();
            return taskList.addTask(new Deadline(words[0].substring(8).trim(), date));
        } else if (input.toLowerCase().matches("\\bevent\\b.*")) {
            // Add event task to task list
            String[] words = input.split("/from+");
            String[] from = words[1].split("/to");
            String date1 = from[0].trim();
            String date2 = from[1].trim();
            return taskList.addTask(new Event(words[0].substring(5).trim(), date1, date2));
        } else if (input.toLowerCase().matches("\\btodo\\b.*")) {
            // Add todo task to task list
            String[] words = input.split("todo");
            if (words[1].equals("")) {
                throw new RiriException("You are adding nothing to your list");
            }
            return taskList.addTask(new Todo(words[1].trim()));
        } else if (input.toLowerCase().matches("\\bdelete\\b.*")) {
            // Delete tasks in tasks list
            String[] words = input.split("\\s+");
            return taskList.delete(Integer.parseInt(words[1].trim()));
        } else if (input.toLowerCase().matches("\\bfind\\b.*")) {
            // Implement the find function through keyword
            String keyword = input.substring(4);
            return taskList.searchTasks(keyword);
        } else if (input.trim().isEmpty()) {
            // If by mistake user presses return or space, nothing will happen
            return "";
        }
        return "Unable to process or understand command.";
    }
}
