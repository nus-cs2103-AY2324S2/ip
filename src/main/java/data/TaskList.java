package data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import data.exception.CoDriverException;
import parser.Parser;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(String[] taskStrings) {
        this.tasks = new ArrayList<>();
        for (String s : taskStrings) {
            String[] arguments = s.split("\\|");
            String type = arguments[0];
            boolean isDone = arguments[1].equals("1");
            switch (type) {
            case "T": // task.ToDo
                String description = arguments[2];
                Todo t = new Todo(description);
                if (isDone) {
                    t.markDone();
                }
                this.tasks.add(t);
                break;
            case "D": // task.Deadline
                String deadlineDescription = arguments[2];
                LocalDate deadlineDate = Parser.parseDate(arguments[3]);
                Deadline d = new Deadline(deadlineDescription, deadlineDate);
                if (isDone) {
                    d.markDone();
                }
                this.tasks.add(d);
                break;
            case "E": // task.Event
                String eventDescription = arguments[2];
                String[] dates = arguments[3].split("~");
                LocalDate eventFrom = Parser.parseDate(dates[0]);
                LocalDate eventTo = Parser.parseDate(dates[1]);
                Event e = new Event(eventDescription, eventFrom, eventTo);
                if (isDone) {
                    e.markDone();
                }
                this.tasks.add(e);
                break;
            }
        }
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void markTask(int index) throws CoDriverException {
        int listIndex = index - 1;
        if (listIndex >= this.tasks.size()) {
            throw new CoDriverException("Error! Given index exceeds list size of " + this.tasks.size() + ".");
        }
        Task t = this.tasks.get(listIndex);
        t.markDone();
    }

    public void unmarkTask(int index) throws CoDriverException {
        int listIndex = index - 1;
        if (listIndex >= this.tasks.size()) {
            throw new CoDriverException("Error! Given index exceeds list size of " + this.tasks.size() + ".");
        }
        Task t = this.tasks.get(listIndex);
        t.markNotDone();
    }

    public void deleteTask(int index) throws CoDriverException {
        int listIndex = index - 1;
        if (listIndex >= this.tasks.size()) {
            throw new CoDriverException("Error! Given index exceeds list size of " + this.tasks.size() + ".");
        }
        Task t = this.tasks.get(listIndex);
        this.tasks.remove(t);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public String toSaveString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : this.tasks) {
            if (t instanceof Todo) {
                sb.append(t.toSaveString()).append("\n");
            } else if (t instanceof Deadline) {
                sb.append(t.toSaveString()).append("\n");
            } else if (t instanceof Event) {
                sb.append(t.toSaveString()).append("\n");
            }
        }
        return sb.toString();
    }

    public void saveTaskList(String filePath) {

    }

    public static TaskList openTaskList(String filePath) {
        TaskList tl = new TaskList();
        File f = new File(filePath);
        Scanner scanner;
        try {
            scanner = new Scanner(f);
        } catch (FileNotFoundException e) {
            return tl;
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] arguments = line.split("\\|");
            String type = arguments[0];
            boolean isDone = arguments[1].equals("1");
            switch (type) {
            case "T": // task.ToDo
                String description = arguments[2];
                Todo t = new Todo(description);
                if (isDone) {
                    t.markDone();
                }
                tl.tasks.add(t);
                break;
            case "D": // task.Deadline
                String deadlineDescription = arguments[2];
//                String deadlineDate = line.substring(line.indexOf("|") + 2);
                LocalDate deadlineDate = Parser.parseDate(arguments[3]);
                Deadline d = new Deadline(deadlineDescription, deadlineDate);
                if (isDone) {
                    d.markDone();
                }
                tl.tasks.add(d);
                break;
            case "E": // task.Event
                String eventDescription = arguments[2];
                String[] dates = arguments[3].split("~");
                LocalDate eventFrom = Parser.parseDate(dates[0]);
                LocalDate eventTo = Parser.parseDate(dates[1]);
                Event e = new Event(eventDescription, eventFrom, eventTo);
                if (isDone) {
                    e.markDone();
                }
                tl.tasks.add(e);
                break;
            }
        }
        scanner.close();
        return tl;
    }
}
