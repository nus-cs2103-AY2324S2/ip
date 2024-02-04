package badgpt.util;

import badgpt.exceptions.ListEmptyException;
import badgpt.exceptions.ListFullException;
import badgpt.exceptions.SameStatusException;
import badgpt.exceptions.TaskNotFoundException;

import badgpt.tasks.Deadline;
import badgpt.tasks.Event;
import badgpt.tasks.Task;
import badgpt.tasks.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>(100);
    private TasksUi tasksUi = new TasksUi();
    private boolean hasChanges = false;

    public void store(Task task) throws ListFullException {
        if (tasks.size() == 100) {
            throw new ListFullException("Your todo list is currently full.");
        } else {
            tasks.add(task);
            tasksUi.printAddTask(task, tasks.size());
            hasChanges = true;
        }
    }

    public void listTasks() throws ListEmptyException {
        if (tasks.size() == 0) {
            throw new ListEmptyException("Your todo list is currently empty.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                tasksUi.printTask(tasks.get(i), i + 1, tasks.size());
            }
        }
    }

    public void mark(int taskNum) throws TaskNotFoundException, SameStatusException {
        try {
            if (!tasks.get(taskNum).isComplete()) {
                tasks.get(taskNum).complete();
                tasksUi.printMarkUnmarkOutcome(tasks.get(taskNum), 0);
                hasChanges = true;
            } else {
                throw new SameStatusException("The operation cannot be completed.", tasks.get(taskNum), 0);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(e.getMessage(), tasks.size());
        }
    }

    public void unmark(int taskNum) throws TaskNotFoundException, SameStatusException {
        try {
            if (tasks.get(taskNum).isComplete()) {
                tasks.get(taskNum).uncomplete();
                tasksUi.printMarkUnmarkOutcome(tasks.get(taskNum), 1);
                hasChanges = true;
            } else {
                throw new SameStatusException("The operation cannot be completed.", tasks.get(taskNum), 1);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(e.getMessage(), tasks.size());
        }
    }

    public void delete(int taskNum) throws TaskNotFoundException {
        try {
            Task task = tasks.remove(taskNum);
            tasksUi.printDelete(task, tasks.size());
            hasChanges = true;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskNotFoundException(e.getMessage(), tasks.size());
        }
    }

    public void loadData(char type, String descr, String deadline, String from, String to, boolean isComplete) {
        Task task;
        if (type == 'T') {
            task = new ToDo(descr);
        } else if (type == 'E') {
            task = new Event(descr, from, to);
        } else {
            task = new Deadline(descr, deadline);
        }

        if (isComplete) {
            task.complete();
        }

        tasks.add(task);
    }

    public void writeChanges(FileManager fileManager) {
        if (hasChanges) {
            String data = "";
            for (Task task : tasks) {
                data += task.saveTask() + "\n";
            }
            fileManager.writeToFile(data);
        }
    }
}
