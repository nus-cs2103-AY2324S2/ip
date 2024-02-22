package dav;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a list of tasks with various operations like adding, listing, marking as done, etc.
 */
class TaskList {

    private List<Task> tasks;
    private Storage storage;

    /**
     * Constructs a TaskList with the specified list of tasks and storage.
     * @param tasks List of tasks to be initialized with.
     * @param storage Storage object for saving and loading tasks.
     */
    public TaskList(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Constructs an empty TaskList with no tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Finds tasks containing a specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A formatted string listing matching tasks or a message indicating
     *         that no matching tasks were found.
     */
    public String findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            result.append("No matching tasks found.");
        } else {
            result.append("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append("\n ").append(i + 1).append(".").append(matchingTasks.get(i));
            }
        }

        return result.toString();
    }


    /**
     * Adds a new TodoTask with the given description to the task list.
     *
     * @param taskDescription The description of the TodoTask to be added.
     * @return  If the task description is empty, the method returns "Task description is empty."
     *          Otherwise, it returns the result of adding the task.
     */
    public String addTodoTask(String taskDescription) {
        if (taskDescription.isEmpty()) {
            return "Task description is empty.";
        } else {
            TodoTask newTask = new TodoTask(taskDescription);
            return addTask(newTask);
        }
    }

    /**
     * Adds a new DeadlineTask with the given details to the task list.
     *
     * @param taskDetails The details of the DeadlineTask to be added, including
     *                    the description and the deadline in "yyyy-MM-dd HHmm" format.
     * @return If the task details are not in the correct format or if there's an issue with the date or
     *         time parsing, an error message is returned. Otherwise, it returns the result of adding the task.
     */
    public String addDeadlineTask(String taskDetails) {
        String[] details = taskDetails.split(" /by ");

        if (details.length != 2) {
            return "Invalid deadline task format.";
        }

        String description = details[0].trim();
        String dateTime = details[1].trim();

        if (description.isEmpty()) {
            return "No deadline specified.";
        }

        try {
            String[] dateTimeParts = dateTime.split(" ");
            String date = dateTimeParts[0];
            String time = dateTimeParts[1];

            DeadlineTask newTask = new DeadlineTask(description, date, time);
            return addTask(newTask);

        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            return "Invalid date or time format. Please use yyyy-MM-dd HHmm.";
        }
    }

    /**
     * Adds a new EventTask with the given details to the task list.
     *
     * @param taskDetails The details of the EventTask to be added, including the description, the start time,
     *                    and the end time in "yyyy-MM-dd HHmm" format.
     * @return If the task details are not in the correct format or if there's an issue with the date or time parsing,
     *         an error message is returned. Otherwise, it returns the result of adding the task.
     */
    public String addEventTask(String taskDetails) {
        String[] details = taskDetails.split(" /from ");

        if (details.length != 2) {
            return "Invalid event task format.";
        }

        String description = details[0].trim();
        String[] timeDetails = details[1].split(" /to ");

        if (description.isEmpty()) {
            return "No event specified.";
        }

        if (timeDetails.length != 2) {
            return "Invalid event task format.";
        }

        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(timeDetails[0],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime toDateTime = LocalDateTime.parse(timeDetails[1],
                                            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

            EventTask newTask = new EventTask(description, fromDateTime, toDateTime);
            return addTask(newTask);
        } catch (DateTimeParseException e) {
            return "Invalid date or time format. Please use yyyy-MM-dd HHmm.";
        }
    }

    /**
     * Adds a task to the list and prints a confirmation message.
     * @param task Task to be added to the list.
     * @return Message describing the task added and task list.
     */
    public String addTask(Task task) {
        tasks.add(task);
        String result = "Got it. I've added this task:\n   " + task + "\nNow you have " + tasks.size()
                        + " tasks in the list.";
        saveTasks();
        return result;
    }

    /**
     * Lists all the tasks in the list.
     * @return List of tasks in TaskList.
     */
    public String listTasks() {
        StringBuilder result = new StringBuilder();

        if (tasks.isEmpty()) {
            result.append("No tasks added yet.");
        } else {
            result.append("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                result.append("\n ").append(i + 1).append(".").append(tasks.get(i));
            }
        }

        return result.toString();
    }

    /**
     * Marks a task at the specified index as done.
     * @param taskIndex Index of the task to be marked as done.
     * @return Message describing task marked done or if task index is invalid.
     */
    public String markTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            Task task = tasks.get(taskIndex - 1);
            task.markAsDone();
            saveTasks();
            return "Nice! I've marked this task as done:\n   " + task;
        } else {
            return "Invalid task index.";
        }
    }

    /**
     * Unmarks a task at the specified index as not done.
     * @param taskIndex Index of the task to be unmarked.
     * @return Message describing task marked undone or if task index is invalid.
     */
    public String unmarkTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            Task task = tasks.get(taskIndex - 1);
            task.unmarkAsDone();
            saveTasks();
            return "OK, I've marked this task as not done yet:\n   " + task;
        } else {
            return "Invalid task index.";
        }
    }

    /**
     * Deletes a task at the specified index from the list.
     * @param taskIndex Index of the task to be deleted.
     * @return Message describing task deleted or if task index is invalid.
     */
    public String deleteTask(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            Task removedTask = tasks.remove(taskIndex - 1);
            saveTasks();
            return "Task removed:\n   " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.";
        } else {
            return "Invalid task index.";
        }
    }

    /**
     * Checks and lists tasks on a specific date.
     * @param dateString Date in string format (yyyy-MM-dd) to check tasks.
     * @return List of tasks on specific date.
     */
    public String checkTasksOnDate(String dateString) {
        StringBuilder result = new StringBuilder();

        try {
            LocalDateTime targetDate = LocalDateTime.parse(dateString + " 0000",
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            List<Task> tasksOnDate = new ArrayList<>();

            for (Task task : tasks) {
                if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    if (deadlineTask.byDateTime.toLocalDate().isEqual(targetDate.toLocalDate())) {
                        tasksOnDate.add(deadlineTask);
                    }
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    if (eventTask.fromDateTime.toLocalDate().isEqual(targetDate.toLocalDate()) ||
                            eventTask.toDateTime.toLocalDate().isEqual(targetDate.toLocalDate())) {
                        tasksOnDate.add(eventTask);
                    }
                }
            }

            if (tasksOnDate.isEmpty()) {
                result.append("No tasks on ").append(targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            } else {
                result.append("Tasks on ").append(targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .append(":");
                for (int i = 0; i < tasksOnDate.size(); i++) {
                    result.append("\n ").append(i + 1).append(".").append(tasksOnDate.get(i));
                }
            }
        } catch (DateTimeParseException e) {
            result.append("Invalid date format. Please use yyyy-MM-dd.");
        }

        return result.toString();
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    private String saveTasks() {
        try {
            storage.save(tasks);
            return "";
        } catch (DavException e) {
            return "Error saving tasks to file: " + e.getMessage();
        }
    }

    public String exit() {
        return "Goodbye.";
    }
}
