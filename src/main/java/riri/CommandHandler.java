package riri;

import java.io.IOException;

/**
 * Return appropriate Riri response for each user input.
 */
public class CommandHandler {
    private final String exitMessage = "Press the cross on your console";
    private final String badCommandMessage = "I am unable to process and understand your command";
    private final String waitingMessage = "I am here to help with your task list";
    /**
     * Handles the logic for interacting with the user through chat input.
     * @param input The user's input to process and respond to.
     * @param taskList The task list that stores all the tasks created by the user.
     * @return A string representing the response or action taken based on the user's input.
     */
    public String chat(String input, TaskList taskList) throws RiriException, IOException {
        if (input.matches("bye")) {
            return exitMessage;
        } else if (input.toLowerCase().matches("\\blist\\b")) {
            // Print list
            return taskList.returnList();
        } else if (input.toLowerCase().matches("\\bmark\\b.*")) {
            // Mark task as done
            String[] words = input.split("\\s+");
            return taskList.mark(Integer.parseInt(words[1]));
        } else if (input.toLowerCase().matches("\\bunmark\\b.*")) {
            // Mark the task as undone
            String[] words = input.split("\\s+");
            return taskList.unmarked(Integer.parseInt(words[1]));
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
            String[] words = input.split("todo");
            if (words[1].trim().equals("")) {
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
            return waitingMessage;
        }
        return badCommandMessage;
    }
}
