package waffles.tasks;

import java.nio.file.Path;
import java.util.ArrayList;

import waffles.exceptions.WafflesDuplicateException;
import waffles.exceptions.WafflesException;
import waffles.exceptions.WafflesIllegalArgumentException;
import waffles.storage.Storage;

/**
 * The TaskList class represents a list of tasks in the Waffles chatbot application.
 */
public class TaskList {
    private static final String MARK_DONE_MESSAGE = "Nice! I've marked this task as done:%n%s";
    private static final String MARK_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:%n%s";
    private static final String LIST_TASK_MESSAGE =
            "Here are the tasks in your list:%s";
    private static final String ADD_TASK_MESSAGE =
            "Got it. I've added this task:%n%s%nNow you have %d tasks in the list.";
    private static final String MISSING_ARGUMENT_MESSAGE =
            "The command you entered has missing arguments. Please try again!";
    private static final String REMOVE_TASK_MESSAGE =
            "Noted. I've removed this task:%n%s%nNow you have %d tasks in the list.";
    private static final String FIND_TASK_MESSAGE = "Here are the matching tasks in your list:%s";
    private static final String IS_DUPLICATE_MESSAGE = "The tasks you are trying to add is a duplicate!";

    private final ArrayList<Task> taskList;
    private final Storage taskStorage;

    /**
     * Constructs a TaskList object with the specified subpath for file storage.
     *
     * @param subPath The subpath for file storage.
     */
    public TaskList(String subPath) {
        this.taskList = new ArrayList<>();
        String root = System.getProperty("user.dir");
        String path = Path.of(root, subPath).toString();
        this.taskStorage = new Storage(path);
        updateList();
    }

    /**
     * Adds a ToDo task to TaskList.
     *
     * @param toDoDescription The description of the ToDo task.
     * @return String with return message.
     * @throws WafflesIllegalArgumentException If the description is black.
     */
    public String addToDoTask(String toDoDescription) {
        if (toDoDescription.isBlank()) {
            throw new WafflesIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        if (isDuplicate(toDoDescription)) {
            throw new WafflesDuplicateException(IS_DUPLICATE_MESSAGE);
        }
        Task toDo = new ToDo(toDoDescription);
        taskList.add(toDo);
        updateStorage();
        return String.format(ADD_TASK_MESSAGE, toDo, taskList.size());
    }

    /**
     * Adds a Deadline task to TaskList.
     *
     * @param deadlineDescription The description of the Deadline task.
     * @return String with return message.
     * @throws WafflesIllegalArgumentException If the description is blank.
     */
    public String addDeadlineTask(String deadlineDescription) {
        if (deadlineDescription.isBlank()) {
            throw new WafflesIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        if (isDuplicate(deadlineDescription)) {
            throw new WafflesDuplicateException(IS_DUPLICATE_MESSAGE);
        }
        String[] deadlineArgs = deadlineDescription.split(" /by ");
        Task deadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        taskList.add(deadline);
        updateStorage();
        return String.format(ADD_TASK_MESSAGE, deadline, taskList.size());
    }

    /**
     * Adds a Event task to TaskList.
     *
     * @param eventDescription The description of the Event task.
     * @return String with return message.
     * @throws WafflesIllegalArgumentException If the description is blank.
     */
    public String addEventTask(String eventDescription) {
        if (eventDescription.isBlank()) {
            throw new WafflesIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        if (isDuplicate(eventDescription)) {
            throw new WafflesDuplicateException(IS_DUPLICATE_MESSAGE);
        }
        String[] eventArgs = eventDescription.split(" /from ");
        String[] eventTime = eventArgs[1].split(" /to ");
        String startTime = eventTime[0];
        String endTime = eventTime[1];
        Task event = new Event(eventArgs[0], startTime, endTime);
        taskList.add(event);
        updateStorage();
        return String.format(ADD_TASK_MESSAGE, event, taskList.size());
    }

    /**
     * Marks a task in TaskList as completed.
     *
     * @param taskIndex The index of the task in TaskList.
     * @return String with return message.
     * @throws WafflesIllegalArgumentException If the taskIndex is missing.
     */
    public String markTask(String taskIndex) {
        if (taskIndex.isBlank()) {
            throw new WafflesIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsDone();
        updateStorage();
        return String.format(MARK_DONE_MESSAGE, t);
    }

    /**
     * Unmarks a task in TaskList as not completed.
     *
     * @param taskIndex The index of the task in TaskList.
     * @return String with return message.
     * @throws WafflesIllegalArgumentException If the taskIndex is missing.
     */
    public String unmarkTask(String taskIndex) {
        if (taskIndex.isBlank()) {
            throw new WafflesIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsUndone();
        updateStorage();
        return String.format(MARK_UNDONE_MESSAGE, t);
    }

    /**
     * Deletes a task in TaskList.
     *
     * @param taskIndex The index of the task to be deleted in TaskList.
     * @return String with return message.
     * @throws WafflesIllegalArgumentException If the taskIndex is missing.
     */
    public String deleteTask(String taskIndex) {
        if (taskIndex.isBlank()) {
            throw new WafflesIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        Task taskToRemove = taskList.get(Integer.parseInt(taskIndex) - 1);
        taskList.remove(Integer.parseInt(taskIndex) - 1);
        updateStorage();
        return String.format(REMOVE_TASK_MESSAGE, taskToRemove, taskList.size());
    }

    /**
     * Finds tasks that contains the keyword given.
     *
     * @param keyword Keyword to find in the taskList.
     * @return String with return message.
     * @throws WafflesIllegalArgumentException If the keyword is missing.
     */
    public String findTask(String keyword) {
        if (keyword.isBlank()) {
            throw new WafflesIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (Task task : taskList) {
            if (task.description.contains(keyword)) {
                sb.append(String.format("%n%d.%s", count, task));
                count += 1;
            }
        }
        return String.format(FIND_TASK_MESSAGE, sb);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(String.format("%n%d.%s", i + 1, task));
        }
        return String.format(LIST_TASK_MESSAGE, sb);
    }

    /**
     * Syncs the current taskList with the tasks stored in storage.
     * Method will be called once when starting up Waffles.
     */

    private void updateList() {
        try {
            String fileData = taskStorage.loadFileData();
            String[] tasks = fileData.split("\n");
            for (String t : tasks) {
                String[] taskTokens = t.split("\\|");
                for (int j = 0; j < taskTokens.length; j++) {
                    taskTokens[j] = taskTokens[j].trim();
                }
                String command = taskTokens[0];

                if (!command.isEmpty()) {
                    String status = taskTokens[1];
                    String description = taskTokens[2];

                    if (command.equals("T")) {
                        syncListWithStorage("T", status, description, "", "", "");
                    } else if (command.equals("D")) {
                        syncListWithStorage("D", status, description, taskTokens[3], "", "");
                    } else {
                        syncListWithStorage("E", status, description, "", taskTokens[3], taskTokens[4]);
                    }
                }

            }
        } catch (WafflesException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Formats a task stored in storage and adds it to the taskList.
     *
     * @param taskType The type of task
     * @param status Status of the task
     * @param description The task description
     * @param by The deadline to complete deadline tasks by
     * @param from The starting date and time for event tasks
     * @param to The ending date and time for event tasks
     */
    private void syncListWithStorage(String taskType, String status, String description,
                                     String by, String from, String to) {
        if (taskType.equals("T")) {
            taskList.add(new ToDo(description));
        } else if (taskType.equals("D")) {
            String byFormatted;
            String[] dateTimeParts = by.replace("-", "/").split("T");
            String[] time = dateTimeParts[1].split(":");
            byFormatted = dateTimeParts[0] + " " + time[0] + time[1];
            taskList.add(new Deadline(description, byFormatted));
        } else {
            String fromFormatted;
            String toFormatted;

            String[] fromParts = from.replace("-", "/").split("T");
            String[] fromHHmm = fromParts[1].split(":");
            fromFormatted = fromParts[0] + " " + fromHHmm[0] + fromHHmm[1];

            String[] toParts = to.replace("-", "/").split("T");
            String[] toHHmm = toParts[1].split(":");
            toFormatted = toParts[0] + " " + toHHmm[0] + toHHmm[1];
            taskList.add(new Event(description, fromFormatted, toFormatted));
        }

        if (status.equals("X")) {
            markTask(String.valueOf(taskList.size()));
        }
    }

    /**
     * Updates the storage based on tasks in current taskList.
     */
    private void updateStorage() {
        StringBuilder sb = new StringBuilder();
        for (Task t : taskList) {
            sb.append(t.toTaskFileTemplate()).append("\n");
        }
        try {
            taskStorage.saveToFile(sb.toString());
        } catch (WafflesException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isDuplicate(String description) {
        for (Task t: taskList) {
            if (t.description.contains(description)) {
                return true;
            }
        }
        return false;
    }
}
