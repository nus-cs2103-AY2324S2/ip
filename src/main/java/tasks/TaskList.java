package tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import errors.Errors;
import errors.InvalidBanterUsageError;
import errors.InvalidTaskNumberUsageError;
import messages.Messages;

public class TaskList implements Iterable<Task> {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public String addTodo(String description) {
        Todo todo = new Todo(description);
        taskList.add(todo);
        return "Got it. I've added this task:\n" + todo +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }
    
    public String addTodo(String description, boolean isDone) {
        Todo todo = new Todo(description, isDone);
        taskList.add(todo);
        return "Got it. I've added this task:\n" + todo +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public String addDeadline(String description, LocalDateTime dueDate) {
        Deadline deadline = new Deadline(description, dueDate);
        taskList.add(deadline);
        return "Got it. I've added this task:\n" + deadline +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }
    
    public String addDeadline(String description, boolean isDone, LocalDateTime dueDate) {
        Deadline deadline = new Deadline(description, isDone, dueDate);
        taskList.add(deadline);
        return "Got it. I've added this task:\n" + deadline +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public String addEvent(String eventDescription, LocalDateTime start, LocalDateTime end) {
        Event event = new Event(eventDescription, start, end);
        taskList.add(event);
        return "Got it. I've added this task:\n" + event +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }
    
    public String addEvent(String eventDescription, boolean isDone, LocalDateTime start, LocalDateTime end) {
        Event event = new Event(eventDescription, isDone, start, end);
        taskList.add(event);
        return "Got it. I've added this task:\n" + event +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\n" + (i + 1) + ". " + taskList.get(i));
        }
        return sb.toString();
    }

    public String markTaskAsDone(int taskNumber) throws InvalidBanterUsageError {
        try {
            return taskList.get(taskNumber - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberUsageError(Errors.INVALID_TASK_NUMBER, Messages.MARK_USAGE, this);
        }
    }

    public String markTaskAsUndone(int taskNumber) throws InvalidBanterUsageError {
        try {
            return taskList.get(taskNumber - 1).markAsUndone();
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberUsageError(Errors.INVALID_TASK_NUMBER, Messages.UNMARK_USAGE, this);
        }
    }

    public String deleteTask(int taskNumber) throws InvalidBanterUsageError {
        try {
            Task deleted = taskList.remove(taskNumber - 1);
            return "Noted. I've removed this task:\n" + deleted +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberUsageError(Errors.INVALID_TASK_NUMBER, Messages.DELETE_USAGE, this);
        }
    }
    
    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}
