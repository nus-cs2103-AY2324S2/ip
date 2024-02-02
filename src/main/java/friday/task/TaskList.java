package friday.task;

import java.util.ArrayList;
import java.util.List;
import friday.ui.Ui;

/**
 * Represents a list of tasks in the Friday application.
 */
public class TaskList {
    private List<Task> tasks ;
    private Ui ui;

    /**
     * Constructs a TaskList object with an empty list of tasks and initializes UI and Storage components.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.ui = new Ui();
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int length() {
        return tasks.size();
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param userInput The user input specifying the task to be marked as done.
     */
    public void markTask(String userInput) {
        String num = userInput.substring(4).trim();
        if (num.isEmpty()) {
            ui.displayMessage("Error. Unknown task number to be marked.");
        } else {
            int id = Integer.parseInt(num);
            if (id > tasks.size()) {
                ui.displayMessage("Error. Task number does not exist.");
            } else {
                ui.displayMessage("Nice! I have marked this task as done: ");
                tasks.get(id - 1).markAsDone();
                ui.displayMessage(tasks.get(id - 1).toString());
            }
        }
    }

    /**
     * Marks a task as undone based on user input.
     *
     * @param userInput The user input specifying the task to be marked as undone.
     */
    public void unmarkTask(String userInput) {
        String num = userInput.substring(6).trim();
        if (num.isEmpty()) {
            ui.displayMessage("Error. Unknown task number to be unmarked.");
        } else {
            int id = Integer.parseInt(num);
            if (id > tasks.size()) {
                ui.displayMessage("Error. Task number does not exist.");
            } else {
                ui.displayMessage("Okay. I have unmarked this task: ");
                tasks.get(id - 1).markAsUndone();
                ui.displayMessage(tasks.get(id - 1).toString());
            }
        }
    }
    /**
     * Deletes a task based on user input.
     *
     * @param userInput The user input specifying the task number to be deleted.
     */
    public void deleteTask(String userInput) {
        String num = userInput.substring(6).trim();
        if (num.isEmpty()) {
            ui.displayMessage("Error. Unknown task number to be deleted.");
        } else {
            int id = Integer.parseInt(num);
            if (id > tasks.size()) {
                ui.displayMessage("Error. Task number does not exist.");
            } else {
                ui.displayMessage("Okay. I have deleted this task: ");
                ui.displayMessage(tasks.get(id - 1).toString());
                tasks.remove(id - 1);
                ui.displayMessage(displayCounter(tasks.size()));
            }
        }
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
     * Adds a task to the task list based on user input for a Todo.
     *
     * @param userInput The user input specifying the Todo task to be added.
     * @return The added Todo task.
     */
    public Todo addTodo(String userInput) {
        String description = userInput.substring(5).trim();
        if (description.isEmpty()) {
            ui.displayMessage("Error. Cannot add empty Todo.");
            return null;
        } else {
            ui.displayMessage("Got it. I have added this task: ");
            Todo t = new Todo(description);
            ui.displayMessage(t.toString());
            tasks.add(t);
            ui.displayMessage(displayCounter(tasks.size()));
            return t;
        }
    }

    /**
     * Adds a task to the task list based on user input for a Deadline.
     *
     * @param userInput The user input specifying the Deadline task to be added.
     * @return The added Deadline task.
     */
    public Deadline addDeadline(String userInput) {
        int id = userInput.indexOf("/by");
        String description = userInput.substring(8, id).trim();
        if (description.isEmpty()) {
            ui.displayMessage("Error. Cannot add empty Deadline.");
            return null;
        } else {
            String by = userInput.substring(id + 3).trim();
            if (by.isEmpty()) {
                ui.displayMessage("Error. Please indicate end time.");
                return null;
            } else {
                ui.displayMessage("Got it. I have added this task: ");
                Deadline d = new Deadline(description, by);
                ui.displayMessage(d.toString());
                tasks.add(d);
                ui.displayMessage(displayCounter(tasks.size()));
                return d;
            }
        }
    }

    /**
     * Adds a task to the task list based on user input for an Event.
     *
     * @param userInput The user input specifying the Event task to be added.
     * @return The added Event task.
     */
    public Event addEvent(String userInput) {
        int idFrom = userInput.indexOf("/from");
        int idTo = userInput.indexOf("/to");
        String description = userInput.substring(5, idFrom).trim();
        String fromTime = userInput.substring(idFrom + 5, idTo).trim();
        String toTime = userInput.substring(idTo + 3).trim();
        if (description.isEmpty()) {
            ui.displayMessage("Error. Cannot add empty Event.");
            return null;
        } else if (fromTime.isEmpty()) {
            ui.displayMessage("Error. Please indicate start time.");
            return null;
        } else if (toTime.isEmpty()) {
            ui.displayMessage("Error. Please indicate to time.");
            return null;
        } else {
            ui.displayMessage("Got it. I have added this task: ");
            Event e = new Event(description, fromTime, toTime);
            ui.displayMessage(e.toString());
            tasks.add(e);
            ui.displayMessage(displayCounter(tasks.size()));
            return e;
        }
    }

    /**
     * Deletes a task from the task list based on user input.
     *
     * @param index The task number to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
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
