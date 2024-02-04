package tasks;

import core.Ui;
import data.Storage;
import enums.TaskType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.Locale;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> loadedTasks) {
        tasks = loadedTasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(String input, Ui ui, Storage storage) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            ui.showTaskTypes();
            return;
        }

        TaskType taskType;
        Task task = null;

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
        }

        if (task != null) {
            tasks.add(task);
            storage.save(tasks);
            ui.showMessage(ui.getUser() + ", I've added this task:\n  " + task);

            int taskCount = tasks.size();
            ui.showTaskCount(taskCount);
        }
    }

    public void deleteTask(String input, Ui ui, Storage storage) {
        try {
            int idx = Integer.parseInt(input.substring(7)) - 1;
            int taskCount = tasks.size();

            if (idx < 0 || idx >= taskCount) {
                ui.showMessage("No task numbered " + (idx + 1) + ", " + ui.getUser() + "!");
            } else {
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

    public void listTasksOnDate(String input, Ui ui) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            ui.showDateFormatError();
            return;
        }

        LocalDate date;
        try {
            date = LocalDate.parse(parts[1]);
        } catch (DateTimeParseException e) {
            ui.showDateFormatError();
            return;
        }

        int count = 0;
        for (Task task : tasks) {
            if ((task instanceof Deadline && ((Deadline) task).getBy().isEqual(date))
                    || (task instanceof Event && ((Event) task).getFrom().isEqual(date))) {
                if (count == 0) {
                    ui.showMessage(ui.getUser() + ", on "
                            + date.format(DateTimeFormatter.ofPattern("MMM d yyyy").withLocale(Locale.US))
                            + ", you have the following tasks:");
                }
                ui.showMessage(task.toString());
                count++;
            }
        }
        if (count == 0) {
            ui.showMessage(ui.getUser() + ", you have no task on "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy").withLocale(Locale.US)) + "!");
        }
    }

    public void markTask(String input, Ui ui, Storage storage) {
        try {
            int idx = Integer.parseInt(input.substring(5)) - 1;
            if (idx < 0 || idx >= tasks.size() || tasks.get(idx) == null) {
                ui.showNoTaskIndex(idx);
            } else {
                tasks.get(idx).mark();
                storage.save(tasks);
                ui.showMessage(ui.getUser() + "! I've marked this task as done:\n" + tasks.get(idx));
            }
        } catch (NumberFormatException e) {
            ui.showFormatError("mark [task index]");
        }
    }

    public void unmarkTask(String input, Ui ui, Storage storage) {
        try {
            int idx = Integer.parseInt(input.substring(7)) - 1;
            if (idx < 0 || idx >= tasks.size() || tasks.get(idx) == null) {
                ui.showNoTaskIndex(idx);
            } else {
                tasks.get(idx).unmark();
                storage.save(tasks);
                ui.showMessage("Ok, " + ui.getUser() + "! I've undone this task:\n" + tasks.get(idx));
            }
        } catch (NumberFormatException e) {
            ui.showFormatError("unmark [task index]");
        }
    }
}
