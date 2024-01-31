package dibo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> storage;
    private int count;
    private final HashSet<String> taskTypes;

    public TaskList(String[] types, ArrayList<Task> storage) {
        this.storage = storage;
        this.count = storage.size();
        this.taskTypes = new HashSet<>();
        this.taskTypes.addAll(List.of(types));

    }

    public String addTask(String text) throws DiboException {
        String[] tokens = text.split("/");
        String type_and_description = tokens[0];
        String type = type_and_description.split(" ")[0];

        if (!taskTypes.contains(type)) {
            throw new DiboException("Oh no sir! There is no such task type :(");
        }

        String description = type_and_description.substring(type.length());
        if (description.equals("")) {
            throw new DiboException("Oh no sir! We need a description for your task. "
                    + "This will enable us to better keep track of your tasks.");
        }

        Task task;
        switch (type) {
        case "todo":
            task = new ToDo(description);
            break;
        case "deadline":
            try {
                String by = tokens[1].split(" ")[1].trim();
                task = new Deadline(description, by);
            } catch (IndexOutOfBoundsException e) {
                throw new DiboException("Oh no sir! Please state the deadline of the task :D");
            } catch (DateTimeParseException e) {
                throw new DiboException("Oh no sir! Please state the deadline of the task "
                        + "in this format: yyyy-mm-dd");
            }
            break;
        default:
            try {
                String from = tokens[1].split(" ")[1].trim();
                String to = tokens[2].split(" ")[1].trim();
                task = new Event(description, from, to);
            } catch (IndexOutOfBoundsException e) {
                throw new DiboException("Oh no sir! Please state the start and end of the task :D");
            } catch (DateTimeParseException e) {
                throw new DiboException("Oh no sir! Please state the deadline of the task "
                        + "in this format: yyyy-mm-dd");
            }
        }
        this.addTask(task);
        return task.toString();
    }

    public void addTask(Task t) {
        this.storage.add(t);
        this.count++;
    }

    public String getDisplayFormat() {
        StringBuilder list = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < count; ++i) {
            list.append((i + 1));
            list.append(".");
            list.append(storage.get(i).toString());
            list.append("\n");
        }
        return list.toString();
    }

    public String getSaveFormat() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            Task task = storage.get(i);
            list.append(task.getSaveFormat());
            list.append("\n");
        }
        return list.toString();
    }

    public String markTask(int i) {
        Task task = storage.get(i - 1);
        task.markAsDone();
        return task.toString();
    }

    public String unmarkTask(int i) {
        Task task = storage.get(i - 1);
        task.markAsNotDone();
        return task.toString();
    }

    public String deleteTask(int i) {
        Task task = storage.get(i - 1);
        storage.remove(i - 1);
        this.count--;
        return task.toString();
    }

    public int getSize() {
        return this.count;
    }




}