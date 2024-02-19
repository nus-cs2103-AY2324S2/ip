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

    public void findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    /**
     * Adds a TodoTask to the list with the specified description.
     * @param taskDescription Description of the TodoTask.
     */
    public void addTodoTask(String taskDescription) {
        if (taskDescription.isEmpty()) {
            System.out.println("Do nothing?");
        } else {
            addTask(new TodoTask(taskDescription));
        }
    }

    /**
     * Adds a DeadlineTask to the list with the specified details.
     * @param taskDetails Details of the DeadlineTask.
     */
    public void addDeadlineTask(String taskDetails) {
        String[] details = taskDetails.split(" /by ");

        if (details.length != 2) {
            System.out.println("Invalid deadline task format.");
            return;
        }

        String description = details[0].trim();
        String dateTime = details[1].trim();

        if (description.isEmpty()) {
            System.out.println("No deadline?");
            return;
        }

        try {
            String[] dateTimeParts = dateTime.split(" ");
            String date = dateTimeParts[0];
            String time = dateTimeParts[1];

            addTask(new DeadlineTask(description, date, time));
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid date or time format. Please use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Adds an EventTask to the list with the specified details.
     *
     * @param taskDetails Details of the EventTask.
     */
    public void addEventTask(String taskDetails) {
        String[] details = taskDetails.split(" /from ");

        if (details.length != 2) {
            System.out.println("Invalid event task format.");
            return;
        }

        String description = details[0].trim();
        String[] timeDetails = details[1].split(" /to ");

        if (description.isEmpty()) {
            System.out.println("No event?");
            return;
        }

        if (timeDetails.length != 2) {
            System.out.println("Invalid event task format.");
            return;
        }

        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(timeDetails[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime toDateTime = LocalDateTime.parse(timeDetails[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

            addTask(new EventTask(description, fromDateTime, toDateTime));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date or time format. Please use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Adds a task to the list and prints a confirmation message.
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        saveTasks(); // Save tasks after adding
    }

    /**
     * Lists all the tasks in the list.
     */
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks added yet.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Marks a task at the specified index as done.
     * @param taskIndex Index of the task to be marked as done.
     */
    public void markTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            tasks.get(taskIndex - 1).markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(taskIndex - 1));
            saveTasks();
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    /**
     * Unmarks a task at the specified index as not done.
     * @param taskIndex Index of the task to be unmarked.
     */
    public void unmarkTaskDone(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            tasks.get(taskIndex - 1).unmarkAsDone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(taskIndex - 1));
            saveTasks();
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    /**
     * Deletes a task at the specified index from the list.
     * @param taskIndex Index of the task to be deleted.
     */
    public void deleteTask(int taskIndex) {
        if (isValidIndex(taskIndex)) {
            Task removedTask = tasks.remove(taskIndex - 1);
            System.out.println(" Task removed:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            saveTasks();
        } else {
            System.out.println(" Invalid task index.");
        }
    }

    /**
     * Checks and lists tasks on a specific date.
     * @param dateString Date in string format (yyyy-MM-dd) to check tasks.
     */
    public void checkTasksOnDate(String dateString) {
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
                System.out.println("No tasks on " + targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            } else {
                System.out.println("Tasks on " + targetDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ":");
                for (int i = 0; i < tasksOnDate.size(); i++) {
                    System.out.println(" " + (i + 1) + "." + tasksOnDate.get(i));
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index <= tasks.size();
    }

    private void saveTasks() {
        try {
            storage.save(tasks);
        } catch (DukeException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}
