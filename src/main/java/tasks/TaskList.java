package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import core.Ui;
import data.Storage;
import enums.TaskType;

/**
 * The TaskList class manages a list of tasks and provides methods to manipulate them.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList instance with the specified list of tasks.
     *
     * @param loadedTasks The list of tasks loaded from storage.
     */
    public TaskList(List<Task> loadedTasks) {
        tasks = loadedTasks;
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task based on user input to the task list.
     *
     * @param input   The user input specifying the task to be added.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage to save the updated task list.
     */
    public void addTask(String input, Ui ui, Storage storage) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            ui.showTaskTypes();
            return;
        }

        TaskType taskType;
        Task task;

        try {
            taskType = TaskType.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            ui.showTaskTypes();
            return;
        }

        switch (taskType) {
        case TODO:
            task = new ToDo(parts[1]);
            break;
        case DEADLINE:
            String[] info = parts[1].split(" /by ", 2);
            if (info.length < 2) {
                ui.showFormatError("deadline [task] /by [yyyy-mm-dd]");
                return;
            }
            try {
                LocalDate byDate = LocalDate.parse(info[1], DateTimeFormatter.ISO_LOCAL_DATE);
                task = new Deadline(info[0], byDate);
            } catch (DateTimeParseException e) {
                ui.showFormatError("deadline [task] /by [yyyy-mm-dd]");
                return;
            }
            break;
        case EVENT:
            String[] eventParts = parts[1].split(" /from ", 2);
            if (eventParts.length < 2) {
                ui.showFormatError("event [task] /from [yyyy-mm-dd] /to [yyyy-mm-dd]");
                return;
            }
            String[] timeParts = eventParts[1].split(" /to ", 2);
            if (timeParts.length < 2) {
                ui.showFormatError("event [task] /from [yyyy-mm-dd] /to [yyyy-mm-dd]");
                return;
            }
            try {
                LocalDate from = LocalDate.parse(timeParts[0], DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate to = LocalDate.parse(timeParts[1], DateTimeFormatter.ISO_LOCAL_DATE);
                task = new Event(eventParts[0], from, to);
            } catch (DateTimeParseException e) {
                ui.showFormatError("event [task] /from [yyyy-mm-dd] /to [yyyy-mm-dd]");
                return;
            }
            break;
        default:
            ui.showTaskTypes();
            return;
        }

        assert tasks != null : "tasks list must not be null";

        tasks.add(task);
        storage.save(tasks);
        ui.showMessage(ui.getUser() + ", I've added this task:\n  " + task);

        int taskCount = tasks.size();
        ui.showTaskCount(taskCount);
    }

    /**
     * Deletes a task from the task list based on user input .
     *
     * @param input   The user input specifying the task to be deleted.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage to save the updated task list.
     */
    public void deleteTask(String input, Ui ui, Storage storage) {
        try {
            int idx = Integer.parseInt(input.substring(7)) - 1;
            int taskCount = tasks.size();

            if (idx < 0 || idx >= taskCount) {
                ui.showMessage("No task numbered " + (idx + 1) + ", " + ui.getUser() + "!");
            } else {
                assert tasks.get(idx) != null : "Task to delete must not be null";

                Task removed = tasks.remove(idx);
                taskCount--;
                storage.save(tasks);

                ui.showMessage(ui.getUser() + ", I've removed this task:\n  " + removed);
                ui.showTaskCount(taskCount);
            }
        } catch (NumberFormatException e) {
            ui.showFormatError("delete [task index]");
        }
    }

    /**
     * Lists all tasks in the task list.
     *
     * @param ui The user interface for displaying messages.
     */
    public void listTasks(Ui ui) {
        if (tasks.size() == 0) {
            ui.showMessage("There is no task yet, " + ui.getUser() + "!");
        } else {
            ui.showMessage(ui.getUser() + ", your task list has the following tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Processes the input to find tasks by date or keyword.
     * If the input is a date in ISO_LOCAL_DATE format (yyyy-MM-dd), it will search for tasks on that date.
     * If the input is not a date, it will treat it as a keyword and search task descriptions.
     *
     * @param input The search parameter provided by the user, either a date or a keyword.
     * @param ui The Ui instance for user interaction.
     */
    public void find(String input, Ui ui) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            ui.showFindFormatError();
            return;
        }

        String searchInput = parts[1].trim();
        LocalDate date;
        try {
            date = LocalDate.parse(searchInput);
            findTasksByDate(date, ui);
        } catch (DateTimeParseException e) {
            findTasksByKeyword(searchInput, ui);
        }
    }

    /**
     * Finds and displays tasks that are due on the specified date.
     * It will show deadlines and events that occur on the given date.
     *
     * @param date The LocalDate object representing the date to search for.
     * @param ui The Ui instance for user interaction.
     */
    private void findTasksByDate(LocalDate date, Ui ui) {
        int count = 0;
        for (Task task : tasks) {
            boolean isDeadlineOnDate = task instanceof Deadline && ((Deadline) task).getBy().isEqual(date);
            boolean isEventOnDate = task instanceof Event && ((Event) task).getFrom().isEqual(date);

            if (isDeadlineOnDate || isEventOnDate) {
                if (count == 0) {
                    ui.showMessage(ui.getUser() + ", on "
                            + date.format(DateTimeFormatter.ofPattern("MMM d yyyy").withLocale(Locale.US))
                            + ", you have the following tasks:");
                }
                ui.showMessage((count + 1) + "." + task);
                count++;
            }
        }
        if (count == 0) {
            ui.showMessage(ui.getUser() + ", you have no task on "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy").withLocale(Locale.US)) + "!");
        }
    }

    /**
     * Finds and displays tasks that contain the given keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @param ui The Ui instance for user interaction.
     */
    private void findTasksByKeyword(String keyword, Ui ui) {
        List<Task> foundTasks = tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (foundTasks.isEmpty()) {
            ui.showMessage(ui.getUser() + ", I couldn't find any task with the keyword: '" + keyword + "'.");
        } else {
            ui.showMessage(ui.getUser() + ", here are the tasks containing the keyword '" + keyword + "':");
            for (int i = 0; i < foundTasks.size(); i++) {
                ui.showMessage((i + 1) + "." + foundTasks.get(i).toString());
            }
        }
    }

    /**
     * Marks a task as completed based on user input.
     *
     * @param input   The user input specifying the task to be marked as completed.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage to save the updated task list.
     */
    public void markTask(String input, Ui ui, Storage storage) {
        try {
            int idx = Integer.parseInt(input.substring(5)) - 1;
            if (idx < 0 || idx >= tasks.size() || tasks.get(idx) == null) {
                ui.showNoTaskIndex(idx);
            } else {
                assert tasks.get(idx) != null : "Task to mark must not be null";

                tasks.get(idx).mark();
                storage.save(tasks);
                ui.showMessage(ui.getUser() + "! I've marked this task as done:\n" + tasks.get(idx));
            }
        } catch (NumberFormatException e) {
            ui.showFormatError("mark [task index]");
        }
    }

    /**
     * Unmarks a completed task based on user input.
     *
     * @param input   The user input specifying the task to be unmarked.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage to save the updated task list.
     */
    public void unmarkTask(String input, Ui ui, Storage storage) {
        try {
            int idx = Integer.parseInt(input.substring(7)) - 1;
            if (idx < 0 || idx >= tasks.size() || tasks.get(idx) == null) {
                ui.showNoTaskIndex(idx);
            } else {
                assert tasks.get(idx) != null : "Task to unmark must not be null";

                tasks.get(idx).unmark();
                storage.save(tasks);
                ui.showMessage("Ok, " + ui.getUser() + "! I've undone this task:\n" + tasks.get(idx));
            }
        } catch (NumberFormatException e) {
            ui.showFormatError("unmark [task index]");
        }
    }

    /**
     * Assigns a tag to a specific task identified by its index.
     *
     * @param input   the user input containing task index and tag information
     * @param ui      the user interface for displaying messages
     * @param storage the storage component for saving the updated task list
     */
    public void tag(String input, Ui ui, Storage storage) {
        try {
            String[] parts = input.split(" ", 3);
            if (parts.length < 3) {
                ui.showFormatError("tag [task index] #[tag]");
                return;
            }

            int idx = Integer.parseInt(parts[1]) - 1;
            String tag = parts[2];

            if (idx < 0 || idx >= tasks.size() || tasks.get(idx) == null) {
                ui.showNoTaskIndex(idx);
            } else {
                assert tasks.get(idx) != null : "Task to tag must not be null";

                tasks.get(idx).setTag(tag);
                storage.save(tasks); // Save the updated list of tasks with the tag
                ui.showMessage("Ok, " + ui.getUser() + "! I've tagged this task as: "
                        + tag + "\n" + tasks.get(idx));
            }
        } catch (NumberFormatException e) {
            ui.showFormatError("tag [task index] #[tag]");
        }
    }
}
