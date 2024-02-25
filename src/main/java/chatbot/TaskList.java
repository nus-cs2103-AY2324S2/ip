package chatbot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected ArrayList<Task> taskList = new ArrayList<Task>();
    public TaskList() {
    }
    /**
     * Represents the type of task.
     */
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    private TaskType determineTaskType(String input) throws AlfredException {
        assert input != null : "Input should not be null";
        assert !input.isBlank() : "Input should not be blank";
        if (input.startsWith("todo")) {
            return TaskType.TODO;
        } else if (input.startsWith("deadline")) {
            return TaskType.DEADLINE;
        } else if (input.startsWith("event")) {
            return TaskType.EVENT;
        } else {
            throw new AlfredException("Sorry Master Bruce. I don't understand what you mean.");
        }
    }
    /**
     * Adds a task to the task list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        this.taskList.add(task);
    }
    /**
     * Adds a task to the task list.
     * @param input The input from the user.
     * @return The response to the user.
     */
    public String addTaskFromInput(String input) {
        try {
            Task task = parseTaskFromInput(input);
            taskList.add(task);
            String singularTask = taskList.size() == 1 ? "task" : "tasks";
            return String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                    taskList.get(taskList.size() - 1).toString(), taskList.size(), singularTask);
        } catch (AlfredException e) {
            return e.toString();
        }
    }
    private Task parseTaskFromInput(String input) throws AlfredException {
        TaskType taskType = determineTaskType(input);
        switch (taskType) {
        case TODO:
            return createToDoTask(input);
        case DEADLINE:
            return createDeadlineTask(input);
        case EVENT:
            return createEventTask(input);
        default:
            throw new AlfredException("Sorry Master Bruce. I don't understand what you mean.");
        }
    }

    private Task createToDoTask(String input) throws AlfredException {
        if (input.length() <= 5) {
            throw new AlfredException("Sorry Master Bruce. The description of a todo cannot be empty.");
        }
        input = input.substring(5).trim();
        if (input.isEmpty()) {
            throw new AlfredException("Sorry Master Bruce. The description of a todo cannot be empty.");
        }
        return new ToDo(input);
    }

    private Task createDeadlineTask(String input) throws AlfredException {
        if (input.length() <= 9) {
            throw new AlfredException("Sorry Master Bruce. "
                    + "Please specify the description and due-date/time of the deadline by including /by.");
        }
        input = input.substring(9).trim();
        String[] splitResult = input.split("/by", 2);
        String description = splitResult[0].trim();
        if (description.isEmpty()) {
            throw new AlfredException("Sorry Master Bruce. The description of a deadline cannot be empty.");
        }
        String by = null;
        try {
            by = splitResult[1].trim();
            if (by.isEmpty()) {
                throw new AlfredException("Sorry Master Bruce. Please specify the due date or time by including /by.");
            }
            LocalDateTime dateTime = LocalDateTime.parse(by, FORMATTER);
            return new Deadline(description, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AlfredException("Sorry Master Bruce. "
                    + "Please specify the due date or time by including /by due-date.");
        } catch (DateTimeParseException e) {
            throw new AlfredException("Sorry Master Bruce. "
                    + "Please specify the due date or time in the format of dd/MM/yyyy HHmm.");
        }
    }

    private Task createEventTask(String input) throws AlfredException {
        if (input.length() <= 6 || !input.contains("/from") || !input.contains("/to")) {
            throw new AlfredException("Sorry Master Bruce. Please specify the description, start time, "
                    + "and end time of the event by including /from start-time /to end-time.");
        }
        input = input.substring(6).trim();
        // Regular expression patterns
        Pattern descriptionPattern = Pattern.compile("^(.*?)\\s*/from");
        Pattern fromPattern = Pattern.compile("/from\\s+(\\d{2}/\\d{2}/\\d{4}\\s+\\d{4})");
        Pattern toPattern = Pattern.compile("/to\\s+(\\d{2}/\\d{2}/\\d{4}\\s+\\d{4})");
        // Match patterns against input
        Matcher descriptionMatcher = descriptionPattern.matcher(input);
        Matcher fromMatcher = fromPattern.matcher(input);
        Matcher toMatcher = toPattern.matcher(input);
        String descriptionEvent = null;
        String startTime = null;
        String endTime = null;
        // Find description, start time, and end time
        if (descriptionMatcher.find()) {
            descriptionEvent = descriptionMatcher.group(1);
        }
        if (fromMatcher.find()) {
            startTime = fromMatcher.group(1);
        }
        if (toMatcher.find()) {
            endTime = toMatcher.group(1);
        }
        if (descriptionEvent == null || startTime == null || endTime == null) {
            throw new AlfredException("Sorry Master Bruce."
                    + "Please specify both description, start time, and end time.");
        }
        if (descriptionEvent.isEmpty()) {
            throw new AlfredException("Sorry Master Bruce. The description of an event cannot be empty.");
        }
        LocalDateTime startDateTime = LocalDateTime.parse(startTime, FORMATTER);
        LocalDateTime endDateTime = LocalDateTime.parse(endTime, FORMATTER);
        return new Event(descriptionEvent, startDateTime, endDateTime);
    }

    /**
     * Marks a task as done in the task list.
     * @param index The index of the task to be marked as done.
     * @return The response to the user.
     */
    public String markList(int index) throws AlfredException {
        Task task = findIndex(index);
        if (task.isDone()) {
            throw new AlfredException("Sorry Master Bruce. This task has already been marked as done.");
        }
        task.toggleDone();
        return ("Nice! I've marked this task as done:\n  " + task.toString());
    }

    /**
     * Marks a task as not done in the task list.
     * @param index The index of the task to be marked as not done.
     * @return The response to the user.
     */
    public String unmarkList(int index) throws AlfredException {
        Task task = findIndex(index);
        if (!task.isDone()) {
            throw new AlfredException("Sorry Master Bruce. This task has already been marked as not done.");
        }
        task.toggleDone();
        return ("OK, I've marked this task as not done yet:\n  " + task.toString());
    }

    /**
     * Deletes a task from the task list.
     * @param index The index of the task to be deleted.
     * @return The response to the user.
     */
    public String deleteList(int index) throws AlfredException {
        Task task = findIndex(index);
        String removedTask = task.toString();
        taskList.remove(index);
        return ("Noted. I've removed this task:\n  " + removedTask + "\nNow you have "
                + taskList.size() + " tasks in the list.");
    }

    /**
     * Applies a function to each element of the task list.
     * @param mapper The function to apply to each element.
     * @param <R> The type of elements returned by the function.
     * @return A new TaskList containing the results of applying the function to each element.
     */
    public <R extends Task> TaskList map(Function<Task, R> mapper) {
        assert mapper != null : "Mapper should not be null";
        TaskList result = new TaskList();
        for (Task task : taskList) {
            result.addTask(mapper.apply(task));
        }
        return result;
    }

    /**
     * Performs the given action for each element of the task list until all elements have been processed
     * or the action throws an exception.
     * @param action The action to be performed for each element.
     */
    public void forEach(Consumer<Task> action) {
        assert action != null : "Action should not be null";
        for (Task task : taskList) {
            action.accept(task);
        }
    }
    private Task findIndex(int index) throws AlfredException {
        try {
            if (taskList.isEmpty()) {
                throw new AlfredException("Sorry Master Bruce. There are no tasks in the list.");
            } else if (index < 0 || index >= taskList.size()) {
                if (taskList.size() == 1) {
                    throw new AlfredException("Sorry Master Bruce. The task number you have entered is not in the list."
                            + " There is only one item in the list.");
                } else {
                    throw new AlfredException("Sorry Master Bruce. The task number you have entered is not in the list."
                            + " Please enter a number in the range of 1 to " + taskList.size() + ".");
                }
            }
        } catch (NumberFormatException e) {
            throw new AlfredException("Sorry Master Bruce. Please enter a valid number.");
        }
        return taskList.get(index);
    }

    /**
     * Finds tasks in the task list by date.
     * @param dateTime The date and time to be searched for.
     * @return The list of tasks found.
     */
    public TaskList findByDate(LocalDateTime dateTime) {
        assert dateTime != null : "DateTime should not be null";
        TaskList result = new TaskList();
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.isDueDate(dateTime)) {
                    result.addTask(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.isStartTime(dateTime) || event.isEndTime(dateTime)) {
                    result.addTask(event);
                }
            }
        }
        return result;
    }

    /**
     * Finds tasks in the task list by keyword.
     * @param keyword The keyword to be searched for.
     * @return The list of tasks found.
     */
    public TaskList findByKeyword(String keyword) {
        assert keyword != null : "Keyword should not be null";
        TaskList result = new TaskList();
        for (Task task : taskList) {
            if (task.descriptionContains((keyword))) {
                result.addTask(task);
            }
        }
        return result;
    }

    private void sort(Comparator<Task> comparator) {
        taskList.sort(comparator);
    }

    /**
     * Gets the urgent tasks from the task list.
     * @return The list of urgent tasks.
     */
    public TaskList getUrgentTasks() {
        TaskList result = new TaskList();
        TaskList todos = new TaskList();
        TaskList sortedTasks = new TaskList();
        for (Task task : taskList) {
            if (task.isDone()) {
                continue;
            }
            if (task instanceof Deadline) {
                sortedTasks.addTask(task);
            } else if (task instanceof Event) {
                sortedTasks.addTask(task);
            } else if (task instanceof ToDo) {
                todos.addTask(task);
            }
        }
        sortDeadlinesAndEvents(sortedTasks);
        sortedTasks.forEach(result::addTask);
        todos.forEach(result::addTask);
        return result;
    }

    private void sortDeadlinesAndEvents(TaskList taskList) {
        taskList.sort((t1, t2) -> {
            if (t1 instanceof Deadline && t2 instanceof Deadline) {
                return ((Deadline) t1).getBy().compareTo(((Deadline) t2).getBy());
            } else if (t1 instanceof Deadline && t2 instanceof Event) {
                return -1; // Deadline comes before Event
            } else if (t1 instanceof Event && t2 instanceof Deadline) {
                return 1; // Event comes after Deadline
            } else if (t1 instanceof Event && t2 instanceof Event) {
                return ((Event) t1).getStartTime().compareTo(((Event) t2).getStartTime());
            }
            return 0;
        });
    }
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("%d. %s\n", i + 1, taskList.get(i).toString());
        }
        if (output.isEmpty()) {
            return "Sorry Master Bruce. There are no tasks in the list.";
        }
        return output;
    }

}
