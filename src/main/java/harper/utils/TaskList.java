package harper.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import harper.exceptions.HarperInvalidDateTimeException;
import harper.exceptions.HarperInvalidIndexException;
import harper.exceptions.HarperInvalidUpdateException;
import harper.tasks.Deadline;
import harper.tasks.Event;
import harper.tasks.Task;
import harper.tasks.ToDo;

/**
 * The TaskList class handles operations on the task list, such as add task, delete task
 * mark or unmark task and list task.
 */
public class TaskList {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Lists out the tasks in the list as String form.
     *
     * @return String form of the tasks in the list.
     */
    public String listTasks() {
        String tasksString = "";
        int tasksSize = this.tasks.size();
        for (int i = 0; i < tasksSize; i++) {
            int index = i + 1;
            tasksString = tasksString + index + ". " + this.tasks.get(i).toString() + "\n";
        }
        return tasksString;
    }

    /**
     * Lists out the tasks in the list as file form.
     *
     * @return File form of the tasks in the list.
     */
    public String listTasksIntoFile() {
        String tasksString = "";
        for (Task task : this.tasks) {
            tasksString = tasksString + task.toString() + System.lineSeparator();
        }
        return tasksString;
    }

    /**
     * Adds the task into the list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the task with index specified.
     *
     * @param taskIndex Index of the task to be deleted.
     */
    public Task deleteTask(int taskIndex) {
        try {
            Task taskRemoved = this.tasks.remove(taskIndex);
            return taskRemoved;
        } catch (IndexOutOfBoundsException e) {
            assert taskIndex >= this.tasks.size() : "The index is not out of bound";
            throw new HarperInvalidIndexException();
        }
    }

    /**
     * Marks the task with index specified as done.
     *
     * @param taskIndex Index of the task to be marked.
     * @return task.Task that is marked.
     */
    public Task markTask(int taskIndex, boolean isMarked) {
        try {
            Task task = this.tasks.get(taskIndex);
            if (isMarked) {
                task.markAsDone();
            } else {
                task.markAsNotDone();
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            assert taskIndex >= this.tasks.size() : "The index is not out of bound";
            throw new HarperInvalidIndexException();
        }
    }

    /**
     * Returns size of the list.
     *
     * @return Size of the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Finds tasks inside the list that match the keyword.
     *
     * @param keyword Keyword to be checked.
     * @return String representation of the task list that match the keyword.
     */
    public String findTasks(String keyword) {
        String tasksString = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);
            if (task.matchKeyword(keyword)) {
                int index = i + 1;
                tasksString = tasksString + index + ". " + task.toString() + "\n";
            }
        }
        return tasksString;
    }

    /**
     * Updates task's field.
     *
     * @param taskIndex Index of the task in the list.
     * @param field Field to be updated.
     * @return Task that is being updated.
     */
    public Task updateTask(int taskIndex, String field) {
        try {
            Task task = this.tasks.get(taskIndex);
            if (task instanceof ToDo) {
                return handleUpdateTodo((ToDo) task, field);
            } else if (task instanceof Deadline) {
                return handleUpdateDeadline((Deadline) task, field);
            } else if (task instanceof Event) {
                return handleUpdateEvent((Event) task, field);
            } else {
                throw new HarperInvalidUpdateException();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new HarperInvalidIndexException();
        }
    }

    /**
     * Update todo task.
     *
     * @param task Todo task to be updated.
     * @param field Field to be updated.
     * @return Todo task that is being updated.
     */
    public Task handleUpdateTodo(Task task, String field) {
        String[] description = field.split("/description", 2);
        if (!(description.length == 2) || description[1].isEmpty()) {
            throw new HarperInvalidUpdateException();
        }
        task.updateDescription(description[1].trim());
        return task;
    }

    /**
     * Updates deadline task.
     *
     * @param task Deadline task to be updated.
     * @param field Field to be updated.
     * @return Deadline task that is being updated.
     */
    public Task handleUpdateDeadline(Deadline task, String field) {
        String[] description = field.split("/description", 2);
        if (description.length == 2 && !description[1].isEmpty()) {
            task.updateDescription(description[1].trim());
            return task;
        }

        String[] by = field.split("/by", 2);
        if (by.length == 2 && !by[1].isEmpty()) {
            try {
                LocalDateTime deadlineFormatted = LocalDateTime.parse(by[1].trim(), DATE_TIME_FORMATTER);
                task.updateBy(deadlineFormatted);
                return task;
            } catch (DateTimeParseException e) {
                throw new HarperInvalidDateTimeException();
            }
        }
        throw new HarperInvalidUpdateException();
    }

    /**
     * Updates event task.
     *
     * @param task Event task to be updated.
     * @param field Field to be updated.
     * @return Event task that is being updated.
     */
    public Task handleUpdateEvent(Event task, String field) {
        String[] description = field.split("/description", 2);
        if (description.length == 2 && !description[1].isEmpty()) {
            task.updateDescription(description[1].trim());
            return task;
        }

        String[] start = field.split("/from", 2);
        try {
            if (start.length == 2 && !start[1].isEmpty()) {
                LocalDateTime deadlineFormatted = LocalDateTime.parse(start[1].trim(), DATE_TIME_FORMATTER);
                task.updateStart(deadlineFormatted);
                return task;
            }

            String[] end = field.split("/to", 2);
            if (end.length == 2 && !end[1].isEmpty()) {
                LocalDateTime deadlineFormatted = LocalDateTime.parse(end[1].trim(), DATE_TIME_FORMATTER);
                task.updateEnd(deadlineFormatted);
                return task;
            }
        } catch (DateTimeParseException e) {
            throw new HarperInvalidDateTimeException();
        }
        throw new HarperInvalidUpdateException();
    }
}
