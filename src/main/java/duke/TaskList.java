package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class TaskList {
    private List<Task> list;

    private Storage storage;

    public TaskList(Storage storage) {
        this.list = new ArrayList<>();
        this.storage = storage;
    }

    public TaskList(Storage storage, File f) throws FileNotFoundException {
        this.list = new ArrayList<>();
        this.storage = storage;

        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String curr = scanner.nextLine();
            String[] parts = curr.split("\\|");
            for (int i = 0; i < parts.length; ++i) {
                parts[i] = parts[i].trim();
            }

            String taskType = parts[0];
            String done = parts[1];
            String name = parts[2];
            Task newTask;
            if (taskType.equals("T")) {
                newTask = new Todo(name);
            } else if (taskType.equals("D")) {
                newTask = new Deadline(name, parts[3]);
            } else {
                newTask = new Event(name, parts[3], parts[4]);
            }
            if (done.equals("1")) {
                newTask.mark();
            }
            add(newTask);
        }
    }

    public void add(Task task) {
        list.add(task);
        save();
    }

    public Task delete(int index) {
        Task removed = list.remove(index - 1);
        save();
        return removed;
    }

    // Have yet to handle index out of range error
    public void mark(int index, boolean mark) {
        if (mark) {
            list.get(index - 1).mark();
        } else {
            list.get(index - 1).unmark();
        }
        save();
    }

    public int getSize() {
        return list.size();
    }

    public Task getTaskByIndex(int index) {
        return list.get(index - 1);
    }

    private void save() {
        String text = "";
        for (Task t : list) {
            text += t.fileString();
            text += "\n";
        }
        try {
            storage.writeToFile(text);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 1; i <= list.size(); ++i) {
            s += (i + ". " + list.get(i-1).toString() + '\n');
        }
        return s;
    }

}
