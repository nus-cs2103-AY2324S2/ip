package tasks;

import java.util.ArrayList;
import errors.Errors;
import errors.InvalidBanterUsageError;
import errors.InvalidTaskNumberUsageError;

public class TaskList {
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

    public String addDeadline(String description, String dueDate) {
        Deadline deadline = new Deadline(description, dueDate);
        taskList.add(deadline);
        return "Got it. I've added this task:\n" + deadline +
                "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    public String addEvent(String eventDescription, String start, String end) {
        Event event = new Event(eventDescription, start, end);
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
            throw new InvalidTaskNumberUsageError(Errors.INVALID_TASK_NUMBER, Errors.DELETE_USAGE, this);
        }
    }

    public String markTaskAsUndone(int taskNumber) throws InvalidBanterUsageError {
        try {
            return taskList.get(taskNumber - 1).markAsUndone();
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberUsageError(Errors.INVALID_TASK_NUMBER, Errors.UNMARK_USAGE, this);
        }
    }

    public String deleteTask(int taskNumber) throws InvalidBanterUsageError {
        try {
            Task deleted = taskList.remove(taskNumber - 1);
            return "Noted. I've removed this task:\n" + deleted +
                    "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidTaskNumberUsageError(Errors.INVALID_TASK_NUMBER, Errors.DELETE_USAGE, this);
        }
    }
}
