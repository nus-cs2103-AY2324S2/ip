package ken.task;

import ken.exception.KenException;
import ken.response.Response;
import ken.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The TaskList class represents a list of tasks in the task management application.
 *
 * Manages operations related to tasks, such as adding, deleting, and marking tasks.
 */

public class TaskList {

    private static final int MAX_TASKS = 100;
    private final List<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a task list with the given tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */

    Ui ui = new Ui();
    public Response addTask(Task task) {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
            return ui.addTaskMessage(task, tasks.size());

        } else {
            return ui.tooManyTaskMessage();

        }
    }


    /**
     * Adds a todo task to the list.
     *
     * @param description The description of the todo task.
     * @throws KenException If the description is empty.
     */
    public Response addTodoTask(String description) throws KenException {
        if (description.isEmpty()) {
            throw new KenException("do what?");
        }
        Todo todo = new Todo(description);
        return addTask(todo);
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param description The description of the deadline task.
     * @throws KenException If the description or deadline command is invalid.
     */
    public Response addDeadlineTask(String description) throws KenException {
        try {
            int indexOfBy = description.indexOf("/by");
            if (indexOfBy != -1) {
                String deadlineDescription = description.substring(0, indexOfBy).trim();
                String by = description.substring(indexOfBy + 3).trim();
                Deadline deadline = new Deadline(deadlineDescription, by);
                return addTask(deadline);
            } else {
                ui.invalidDeadlineMessage();
                throw new KenException("Invalid deadline command. By when?.");
            }
        } catch (Exception e) {
            throw new KenException("Invalid deadline command. By when?.");
        }
    }


    /**
     * Adds an event task to the list.
     *
     * @param description The description of the event task.
     * @throws KenException If the description or event command is invalid.
     */
    public Response addEventTask(String description) throws KenException {
        try {
            int indexOfFrom = description.indexOf("/from");
            int indexOfTo = description.indexOf("/to");

            if (indexOfFrom != -1 && indexOfTo != -1) {
                String eventDescription = description.substring(0, indexOfFrom).trim();
                String from = description.substring(indexOfFrom + 5, indexOfTo).trim();
                String to = description.substring(indexOfTo + 3).trim();
                Event event = new Event(eventDescription, from, to);
                return addTask(event);
            } else {
                ui.invalidEventMessage();
                throw new KenException("Invalid event command. From when to when?");
            }
        } catch (Exception e) {
            throw new KenException("Invalid event command. From when to when?");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     */
    public Response deleteTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            return ui.deleteMessage(removedTask, tasks.size());
        } else {
            return ui.invalidTaskMessage(index);
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public Response markTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            return ui.markTaskMessage(index, task.toString());
        } else {
            return ui.invalidTaskMessage(index);
        }
    }

    /**
     * Unmarks a task as done.
     *
     * @param index The index of the task to be unmarked as done.
     */
    public Response unmarkTask(int index) {
        if (index >= 1 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.unmarkAsDone();
            return ui.unmarkTaskMessage(index, task.toString());

        } else {
            return ui.invalidTaskMessage(index);
        }
    }

    /**
     * Lists all tasks in the task list.
     */
    public Response listTasks() {
        return ui.listTasksMessage(tasks.isEmpty(), tasks);
    }

    /**
     * Finds tasks containing the specified keyword in their descriptions and displays the matching tasks.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public Response findTasks(String keyword) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        return ui.displayMatchingTasksMessage(keyword, matchingTasks);
    }
    
}
