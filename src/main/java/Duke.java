import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./src/main/java/data.txt";
    private static List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke \nWhat can I do for you?\n");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            String[] splitInput = input.split(" ", 2);

            try {
                if ("bye".equals(splitInput[0])) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if ("list".equals(splitInput[0])) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if ("mark".equals(splitInput[0])) {
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    tasks.get(index).mark();
                    System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
                    saveTasks();
                } else if ("unmark".equals(splitInput[0])) {
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    tasks.get(index).unMark();
                    System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
                    saveTasks();
                } else if ("todo".equals(splitInput[0])) {
                    Todo newTodo = new Todo(splitInput[1]);
                    tasks.add(newTodo);
                    System.out.println("Got it. I've added this task:\n  " + newTodo + "\nNow you have " + tasks.size() + " tasks in the list");
                    saveTasks();
                } else if ("deadline".equals(splitInput[0])) {
                    String[] deadlineSplit = splitInput[1].split(" /by ");
                    Deadline newDeadline = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                    tasks.add(newDeadline);
                    System.out.println("Got it. I've added this task:\n  " + newDeadline + "\nNow you have " + tasks.size() + " tasks in the list");
                    saveTasks();
                } else if ("event".equals(splitInput[0])) {
                    String[] eventSplit = splitInput[1].split(" /from | /to ");
                    Event newEvent = new Event(eventSplit[0], eventSplit[1], eventSplit[2]);
                    tasks.add(newEvent);
                    System.out.println("Got it. I've added this task:\n  " + newEvent + "\nNow you have " + tasks.size() + " tasks in the list");
                    saveTasks();
                } else if ("delete".equals(splitInput[0])) {
                    int index = Integer.parseInt(splitInput[1]) - 1;
                    Task toDelete = tasks.get(index);
                    tasks.remove(toDelete);
                    System.out.println("Noted. I've removed this task:\n " + toDelete + "\nNow you have " + tasks.size() + " tasks in the list");
                    saveTasks();
                } else {
                    System.out.println("Error! Command not found");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error! Description to command not found");
            }
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_PATH);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        Todo todo = new Todo(parts[2]);
                        if (parts[1].equals("1")) todo.mark();
                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(parts[2], parts[3]);
                        if (parts[1].equals("1")) deadline.mark();
                        tasks.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(parts[2], parts[3], parts[4]);
                        if (parts[1].equals("1")) event.mark();
                        tasks.add(event);
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private static void saveTasks() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : tasks) {
                writer.write(taskToFileString(task));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static String taskToFileString(Task task) {
        String type = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
        String status = task.isDone ? "1" : "0";
        String description = task.description;
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return type + " | " + status + " | " + description + " | " + deadline.by;
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return type + " | " + status + " | " + description + " | " + event.start + " | " + event.end;
        } else {
            return type + " | " + status + " | " + description;
        }
    }

    // Task and its subclasses (Todo, Deadline, Event)
    static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void mark() {
            isDone = true;
        }

        public void unMark() {
            isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X]" : "[ ]");
        }

        @Override
        public String toString() {
            return getStatusIcon() + " " + description;
        }
    }

    static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    static class Event extends Task {
        protected String start;
        protected String end;

        public Event(String description, String start, String end) {
            super(description);
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
        }
    }
}
