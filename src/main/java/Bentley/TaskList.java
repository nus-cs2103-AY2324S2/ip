package bentley;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a list of tasks in the Bentley task management application.
 * Tasks can include Todo, Deadline, and Event types.
 */
public class TaskList {

    /**
     * Represents a generic task with a description and completion status.
     */
    static class Task {
        private String taskDescription;
        private boolean isDone;

        /**
         * Constructs a Task object with the specified description and sets its
         * completion status to false.
         *
         * @param taskDescription The description of the task.
         */
        public Task(String taskDescription) {
            this.taskDescription = taskDescription;
            this.isDone = false;
        }

        /**
         * Returns a string indicating whether the task is done or not (1 for done, 0
         * for not done).
         *
         * @return A string indicating the completion status of the task.
         */
        public String doneOrNot() {
            return (isDone ? "1" : "0");
        }

        /**
         * Marks the task as done.
         */
        public void markAsDone() {
            this.isDone = true;
        }

        /**
         * Marks the task as not done yet.
         */
        public void markAsUndone() {
            this.isDone = false;
        }

        /**
         * Gets the description of the task.
         *
         * @return The description of the task.
         */
        public String getDescription() {
            return taskDescription;
        }

        /**
         * Converts the task to a formatted string representation.
         *
         * @return A string representation of the task.
         */
        @Override
        public String toString() {
            String taskType = "";

            if (taskDescription.startsWith("T") || taskDescription.startsWith("t")) {
                taskType = "T";
            } else if (taskDescription.startsWith("D") || taskDescription.startsWith("d")) {
                taskType = "D";
            } else if (taskDescription.startsWith("E") || taskDescription.startsWith("e")) {
                taskType = "E";
            }

            int substringLength = 0;
            if (taskType.equals("T")) {
                substringLength = 5;
            } else if (taskType.equals("D")) {
                substringLength = 9;
            } else if (taskType.equals("E")) {
                substringLength = 6;
            }
            // Add more conditions if needed for other task types

            return taskType + " [" + (isDone ? "X" : " ") + "] " + taskDescription.substring(substringLength);
        }
    }

    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Displays the list of tasks with their respective indices.
     *
     * @return A string representation of the list of tasks.
     */
    public String listTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Adds a Todo task to the list based on user input.
     *
     * @param userInput The user input containing the task description.
     * @return A string indicating the result of adding the Todo task.
     * @throws IllegalArgumentException If the description is missing.
     */
    public String addTodoTask(String userInput) {
        if (userInput.length() <= 5) {
            throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
        }
        String description = userInput.substring(0);
        if (description.isEmpty()) {
            throw new IllegalArgumentException("looks like the description is missing");
        }

        if (isDuplicateDescription(description)) {
            throw new IllegalArgumentException("Task with the same description already exists");
        }

        Task newTask = new Task(description);

        tasks.add(newTask);
        String result = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + tasks.size()
                + " tasks in the list.";
        return result;
    }

    /**
     * Checks if a task with the given description already exists.
     *
     * @param description The description to check for duplicates.
     * @return true if a task with the same description exists, false otherwise.
     */
    private boolean isDuplicateDescription(String description) {
        for (Task task : tasks) {
            if (task.getDescription().trim().equalsIgnoreCase(description.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a Deadline task to the list based on user input.
     *
     * @param userInput The user input containing the task description and deadline.
     * @return A string indicating the result of adding the Deadline task.
     * @throws IllegalArgumentException If the description or deadline is missing.
     */
    public String addDeadlineTask(String userInput) {
        if (userInput.length() <= 9) {
            throw new IllegalArgumentException("Looks like something is missing (description/ Deadline)");
        }

        String[] parts = userInput.split("/by");
        String description = parts[0].trim();
        String date = parts[1].trim();

        if (description.isEmpty() || date.isEmpty()) {
            throw new IllegalArgumentException("Looks like something is missing (description/ Deadline)");
        }

        LocalDate deadlineDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Task newTask = new Task(description + " | " + deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
        tasks.add(newTask);

        String result = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + tasks.size()
                + " tasks in the list.";
        return result;
    }

    /**
     * Adds an Event task to the list based on user input.
     *
     * @param userInput The user input containing the task description and event
     *                  dates.
     * @return A string indicating the result of adding the Event task.
     * @throws IllegalArgumentException If the description or event dates are
     *                                  missing.
     */
    public String addEventTask(String userInput) {
        if (userInput.length() <= 6) {
            throw new IllegalArgumentException("Looks like something is missing (description/ Deadline)");
        }

        String[] parts = userInput.substring(0).split("/from");
        String description = parts[0].trim();
        String[] eventParts = parts[1].trim().split("/to");
        String from = eventParts[0].trim();
        String to = eventParts[1].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new IllegalArgumentException(
                    "Looks like something is missing (description/ start date/ end date)");
        }

        LocalDateTime fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        Task newTask = new Task(description + " | " +
                "From " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                " to " + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));

        tasks.add(newTask);

        String result = "Got it. I've added this task:\n  " + newTask + "\nNow you have " + tasks.size()
                + " tasks in the list.";
        return result;
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param userInput The user input containing the task index to mark as done.
     * @return A string indicating the result of marking the task as done.
     */
    public String markAsDone(String userInput) {
        StringBuilder result = new StringBuilder("Nice! I've marked this task as done:\n");

        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            Task task = tasks.get(taskNumber - 1);
            task.markAsDone();

            for (int i = 0; i < tasks.size(); i++) {
                result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }

            return result.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // Handle invalid input gracefully
            return "There is no such task number. Please provide a valid task number.";
        }
    }

    /**
     * Marks a task as not done yet based on user input.
     *
     * @param userInput The user input containing the task index to mark as not done
     *                  yet.
     * @return A string indicating the result of marking the task as not done yet.
     */
    public String markAsUndone(String userInput) {
        StringBuilder result = new StringBuilder("OK, I've marked this task as not done yet:\n");

        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            Task task = tasks.get(taskNumber - 1);
            task.markAsUndone();

            for (int i = 0; i < tasks.size(); i++) {
                result.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }

            return result.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            // Handle invalid input gracefully
            return "There is no such task number. Please provide a valid task number.";
        }
    }

    /**
     * Deletes a task from the list based on user input.
     *
     * @param userInput The user input containing the task index to delete.
     * @return A string indicating the result of deleting the task.
     */
    public String deleteTask(String userInput) {
        StringBuilder result = new StringBuilder();

        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);

            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                Task removedTask = tasks.remove(taskNumber - 1);
                result.append("Noted. I've removed this task:\n")
                        .append("  ").append(removedTask).append("\n")
                        .append("Now you have ").append(tasks.size()).append(" tasks in the list.");
            } else {
                result.append("Invalid task index. Please provide a valid index.");
            }

            return result.toString();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            return "There is no such task number. Please provide a valid task number.";
        }
    }

     /**
     * Finds tasks containing a given keyword and displays them.
     *
     * @param userInput The user input containing the keyword to search for.
     * @return A string containing the matching tasks or a message if no tasks match the keyword.
     */
    public String findTasks(String userInput) {

        String[] parts = userInput.split(" ", 2); // Split into two parts, limit to 2 parts
        String keyword = parts.length > 1 ? parts[1] : "";

        StringBuilder result = new StringBuilder();

        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                if (count == 0) {
                    result.append("Here are the matching tasks in your list:\n");
                }
                result.append((i + 1)).append(". ").append(currentTask).append("\n");
                count++;
            }
        }

        if (count == 0) {
            result.append("No matching tasks found.");
        }

        return result.toString();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {

        return tasks;
    }
}