package friday.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import friday.storage.Storage;
import friday.ui.Ui;

/**
 * Represents a list of tasks in the Friday application.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Ui ui;
    private Storage storage;

    /**
     * Constructs a TaskList object with an empty list of tasks and initializes UI and Storage components.
     *
     * @param filePath The file path where task data is stored.
     */
    public TaskList(String filePath) {
//        this.tasks = new ArrayList<>();
        this.ui = new Ui();
        this.storage = new Storage(filePath);
    }

//    public TaskList() {
//       this.tasks = new ArrayList<>();
//    }
    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getLength() {
        return tasks.size();
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param userInput The user input specifying the task to be marked as done.
     * @return A message indicating the task has been marked as done, or an error message.
     */
    public String markTask(String userInput) {
        if (userInput.substring(4).trim().isEmpty()) {
            return "Error. Unknown task number to be marked.";
        }
        String[] nums = userInput.substring(4).split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I will mark these tasks as done: ");
        sb.append(System.lineSeparator());
        for (String num : nums) {
            int id = Integer.parseInt(num.trim());
            if (id > tasks.size()) {
                return "Error. Task number does not exist.";
            } else {
                tasks.get(id - 1).markAsDone();
                sb.append(tasks.get(id - 1).toString() + System.lineSeparator());
            }
        }
        return sb.toString();
    }

    /**
     * Marks a task as undone based on user input.
     *
     * @param userInput The user input specifying the task to be marked as undone.
     * @return A message indicating the task has been marked as undone, or an error message.
     */
    public String unmarkTask(String userInput) {
        if (userInput.substring(6).trim().isEmpty()) {
            return "Error. Unknown task number to be unmarked.";
        }
        String[] nums = userInput.substring(6).split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("Okay. I have unmarked these tasks: ");
        sb.append(System.lineSeparator());
        for (String num : nums) {
            int id = Integer.parseInt(num.trim());
            if (id > tasks.size()) {
                return "Error. Task number does not exist.";
            } else {
                tasks.get(id - 1).markAsUndone();
                sb.append(tasks.get(id - 1).toString() + System.lineSeparator());
            }
        }
        return sb.toString();
    }

    /**
     * Deletes a task based on user input.
     *
     * @param userInput The user input specifying the task number to be deleted.
     * @return A message indicating the task has been deleted, or an error message.
     */
    public String deleteTask(String userInput) {
        if (userInput.substring(6).trim().isEmpty()) {
            return "Error. Unknown task number to be deleted.";
        }
        String[] nums = userInput.substring(7).split(",");
        Integer[] idsToDelete = new Integer[nums.length];
        int pos = 0;
        StringBuilder sb = new StringBuilder("Okay. I am going to delete: ");
        sb.append(System.lineSeparator());
        for (String num : nums) {
            int id = Integer.parseInt(num.trim());
            if (id > tasks.size()) {
                return "Error. Task number does not exist.";
            } else {
                sb.append(tasks.get(id - 1).toString() + System.lineSeparator());
                idsToDelete[pos] = id - 1;
                pos++;
            }
        }
        Arrays.sort(idsToDelete);
        Collections.reverse(Arrays.asList(idsToDelete)); // Sort the ids of the tasks to be deleted in reverse order
        // so that deleting from the back will not affect the id of other tasks.
        for (int i : idsToDelete) {
            tasks.remove(i);
        }
        sb.append(displayCounter(tasks.size()));
        return sb.toString();

    }

    /**
     * Retrieves a task from the task list based on the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index, or null if the index is out of bounds.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            ui.displayMessage("Error. No such task in the task list.");
            return null;
        }
    }

    /**
     * Adds a task to the task list based on data loaded from a file.
     *
     * @param task The task to be added to the task list.
     */
    public void addTaskFromData(Task task) {
        tasks.add(task);
    }

    /**
     * Adds a Todo task to the task list based on user input.
     *
     * @param userInput The user input specifying the Todo task to be added.
     * @return A message indicating the task has been added successfully or an error message.
     */
    public String addTodo(String userInput) {
        if (tasks == null || storage == null) {
            return "Error. TaskList or Storage is not initialized.";
        }

        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            return "Error. Cannot add empty Todo.";
        } else {
            StringBuilder sb = new StringBuilder("Got it. I have added this task: ");
            sb.append(System.lineSeparator());
            Todo t = new Todo(description);
            try {
                storage.appendToFile(t + System.lineSeparator());
            } catch (IOException e) {
                return e.getMessage();
            }
            sb.append(t.toString() + System.lineSeparator());
            tasks.add(t);
            sb.append(displayCounter(tasks.size()));
            return sb.toString();
        }
    }

    /**
     * Adds a Deadline task to the task list based on user input.
     *
     * @param userInput The user input specifying the Deadline task to be added.
     * @return A message indicating the task has been added successfully or an error message.
     */
    public String addDeadline(String userInput) {
        int id = userInput.indexOf("/by");
        String description = userInput.substring(8, id).trim();
        if (description.isEmpty()) {
            return "Error. Cannot add empty Deadline.";
        } else {
            String by = userInput.substring(id + 3).trim();
            if (by.isEmpty()) {
                return "Error. Please indicate end time.";
            } else {
                StringBuilder sb = new StringBuilder("Got it. I have added this task: ");
                sb.append(System.lineSeparator());
                Deadline d = new Deadline(description, by);
                sb.append(d + System.lineSeparator());
                tasks.add(d);
                try {
                    storage.appendToFile(d + System.lineSeparator());
                } catch (IOException e) {
                    return e.getMessage();
                }
                sb.append(displayCounter(tasks.size()));
                return sb.toString();
            }
        }
    }

    /**
     * Adds an Event task to the task list based on user input.
     *
     * @param userInput The user input specifying the Event task to be added.
     * @return A message indicating the task has been added successfully or an error message.
     */
    public String addEvent(String userInput) {
        int idFrom = userInput.indexOf("/from");
        int idTo = userInput.indexOf("/to");
        String description = userInput.substring(5, idFrom).trim();
        String fromTime = userInput.substring(idFrom + 5, idTo).trim();
        String toTime = userInput.substring(idTo + 3).trim();
        if (description.isEmpty()) {
            return "Error. Cannot add empty Event.";
        } else if (fromTime.isEmpty()) {
            return "Error. Please indicate start time.";
        } else if (toTime.isEmpty()) {
            return "Error. Please indicate to time.";
        } else {
            StringBuilder sb = new StringBuilder("Got it. I have added this task: ");
            sb.append(System.lineSeparator());
            Event e = new Event(description, fromTime, toTime);
            sb.append(e.toString() + System.lineSeparator());
            tasks.add(e);
            try {
                storage.appendToFile(e + System.lineSeparator());
            } catch (IOException err) {
                System.out.println(err.getMessage());
            }
            sb.append(displayCounter(tasks.size()));
            return sb.toString();
        }
    }

    /**
     * Searches for tasks containing the specified keyword in the task list.
     *
     * @param userInput The user input containing the search command and keyword.
     *                  Format: "find keyword"
     * @return A message listing the matching tasks or an error message if no tasks match the keyword.
     */
    public String searchTask(String userInput) {
        String keyword = userInput.substring(5).trim();
        List<Task> matchingTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.containsKeyword(keyword)) {
                matchingTasks.add(t);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "Oops. There are no matching tasks.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list: ");
            sb.append(System.lineSeparator());
            for (int i = 1; i <= matchingTasks.size(); i++) {
                sb.append(i + ". " + matchingTasks.get(i - 1).toString());
                sb.append(System.lineSeparator());
            }
            return sb.toString();
        }
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Generates a message indicating the number of tasks in the list.
     *
     * @param num The number of tasks in the list.
     * @return A message indicating the number of tasks in the list.
     */
    private String displayCounter(int num) {
        if (num <= 1) {
            return "Now you have " + num + " task in the list.";
        } else {
            return "Now you have " + num + " tasks in the list.";
        }
    }
}
