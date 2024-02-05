import java.util.Scanner;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Lia {
    private static final String FILE_NAME = "Lia.txt";
    private static final String FOLDER_NAME = "data";
    private static final String PROJECT_FOLDER = ".";
    private static final Path FILE_PATH = Paths.get(PROJECT_FOLDER, FOLDER_NAME, FILE_NAME);
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasks();
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Lia :)");
        System.out.println("What can I do for you?");

        while (true) {
            String input = sc.nextLine();
            try {
                if (input.equals("exit")) {
                    break;
                } else if (input.equals("list")) {
                    if (tasks.isEmpty()) {
                        throw new LiaException("The task list is currently empty.");
                    }

                    for (int j = 1; j <= tasks.size(); j++) {
                        Task task = tasks.get(j - 1);
                        if (task instanceof Todo) {
                            System.out.println(j + ". [" + task.getTaskIcon() + "]" +
                                    "[" + task.getStatusIcon() + "] " + task.description);
                        } else if (task instanceof Deadline) {
                            System.out.println(j + ". [" + task.getTaskIcon() + "]" +
                                    "[" + task.getStatusIcon() + "] " + task.description +
                                    " (by: " + ((Deadline) task).date + ")");
                        } else if (task instanceof Event) {
                            System.out.println(j + ". [" + task.getTaskIcon() + "]" +
                                    "[" + task.getStatusIcon() + "] " + task.description +
                                    " (from: " + ((Event) task).start + " to: " + ((Event) task).end + ")");
                        }
                    }
                } else if (input.startsWith("mark")) {
                    String[] tokens = input.split(" ");
                    int pos = Integer.parseInt(tokens[1]);
                    if (pos <= 0 || pos > tasks.size()) {
                        throw new LiaException("There are only " + tasks.size() + " tasks in the list");
                    }

                    Task task = tasks.get(pos - 1);
                    task.markAsDone();
                    System.out.println("I've marked this task as done:");
                    if (task instanceof Todo) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description);
                    } else if (task instanceof Deadline) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (by: " + ((Deadline) task).date + ")");
                    } else if (task instanceof Event) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (from: " + ((Event) task).start + " to: " + ((Event) task).end + ")");
                    }
                } else if (input.startsWith("unmark")) {
                    String[] tokens = input.split(" ");
                    int pos = Integer.parseInt(tokens[1]);
                    if (pos <= 0 || pos > tasks.size()) {
                        throw new LiaException("There are only " + tasks.size() + " tasks in the list");
                    }

                    Task task = tasks.get(pos - 1);
                    task.markAsNotDone();
                    System.out.println("I've marked this task as not done:");
                    if (task instanceof Todo) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description);
                    } else if (task instanceof Deadline) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (by: " + ((Deadline) task).date + ")");
                    } else if (task instanceof Event) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (from: " + ((Event) task).start + " to: " + ((Event) task).end + ")");
                    }
                } else if (input.startsWith("todo")) {
                    String todo = input.replaceFirst("todo", "").trim();
                    if (todo.isEmpty()) {
                        throw new LiaException("Task description cannot be empty.");
                    }

                    tasks.add(new Todo(todo, false));
                    System.out.println("I've added this task:");
                    Task todoTask = tasks.get(tasks.size() - 1);
                    System.out.println("[" + todoTask.getTaskIcon() + "]" +
                            "[" + todoTask.getStatusIcon() + "] " + todoTask.description);
                    System.out.println("You have " + tasks.size() + " task(s) in the list.");
                } else if (input.startsWith("deadline")) {
                    String deadline = input.replaceFirst("deadline", "").split("/by")[0].trim();
                    if (deadline.isEmpty()) {
                        throw new LiaException("Task description cannot be empty.");
                    }

                    String date = input.split("/by")[1].trim();
                    tasks.add(new Deadline(deadline, date, false));
                    System.out.println("I've added this task:");
                    Task deadlineTask = tasks.get(tasks.size() - 1);
                    System.out.println("[" + deadlineTask.getTaskIcon() + "]" +
                            "[" + deadlineTask.getStatusIcon() + "] " + deadlineTask.description +
                            " (by: " + ((Deadline) deadlineTask).date + ")");
                    System.out.println("You have " + tasks.size() + " task(s) in the list.");
                } else if (input.startsWith("event")) {
                    String event = input.replaceFirst("event", "").split("/from")[0].trim();
                    if (event.isEmpty()) {
                        throw new LiaException("Event description cannot be empty.");
                    }

                    String range = input.split("/from")[1].trim();
                    String start = range.split("/to")[0].trim();
                    String end = range.split("/to")[1].trim();
                    tasks.add(new Event(event, start, end, false));
                    System.out.println("I've added this task:");
                    Task eventTask = tasks.get(tasks.size() - 1);
                    System.out.println("[" + eventTask.getTaskIcon() + "]" +
                            "[" + eventTask.getStatusIcon() + "] " + eventTask.description +
                            " (from: " + ((Event) eventTask).start + " to: " + ((Event) eventTask).end + ")");
                    System.out.println("You have " + tasks.size() + " task(s) in the list.");
                } else if (input.startsWith("delete")) {
                    String[] tokens = input.split(" ");
                    int pos = Integer.parseInt(tokens[1]);
                    if (pos <= 0 || pos > tasks.size()) {
                        throw new LiaException("There are only " + tasks.size() + " tasks in the list.");
                    }

                    Task task = tasks.get(pos - 1);
                    tasks.remove(pos - 1);
                    System.out.println("I've removed this task:");
                    if (task instanceof Todo) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description);
                    } else if (task instanceof Deadline) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (by: " + ((Deadline) task).date + ")");
                    } else if (task instanceof Event) {
                        System.out.println("[" + task.getTaskIcon() + "]" +
                                "[" + task.getStatusIcon() + "] " + task.description +
                                " (from: " + ((Event) task).start + " to: " + ((Event) task).end + ")");
                    }
                    System.out.println("You have " + tasks.size() + " task(s) in the list.");
                } else if (input.equals("help")) {
                    System.out.println("list\n" +
                            "- Lists out all your tasks\n" +
                            "todo <task description>\n" +
                            "- Adds a task\n" +
                            "deadline <task description> /by <due by>\n" +
                            "- Adds a task with a deadline\n" +
                            "event <event description> /from <starts at> to <ends at>\n" +
                            "- Adds an event\n" +
                            "mark <task number>\n" +
                            "- Marks task at specified position as done\n" +
                            "unmark <task number>\n" +
                            "- Marks task at specified position as not done\n" +
                            "delete <task number>\n" +
                            " - Deletes task at specified position\n" +
                            "exit\n" +
                            "- Ends the conversation");
                } else {
                    throw new LiaException("Invalid command. Type help for a list of valid commands and their usages.");
                }
                saveTasks();
            } catch (LiaException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }

    private static void loadTasks() {
        try {
            Files.createDirectories(FILE_PATH.getParent());
            try (BufferedReader br = Files.newBufferedReader(FILE_PATH)) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String description = parts[2];

                    switch (type) {
                        case "T":
                            tasks.add(new Todo(description, isDone));
                            break;
                        case "D":
                            String by = parts[3];
                            tasks.add(new Deadline(description, by, isDone));
                            break;
                        case "E":
                            String start = parts[3];
                            String end = parts[4];
                            tasks.add(new Event(description, start, end, isDone));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    private static void saveTasks() {
        try {
            Files.createDirectories(FILE_PATH.getParent());
            try (BufferedWriter bw = Files.newBufferedWriter(FILE_PATH)) {
                for (Task task : tasks) {
                    String taskSave;
                    if (task instanceof Todo) {
                        taskSave = "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                    } else if (task instanceof Deadline) {
                        taskSave = "D | " + (task.isDone() ? "1" : "0") + " | " +
                                task.getDescription() + " | " + ((Deadline) task).getDate();
                    } else if (task instanceof Event) {
                        taskSave = "E | " + (task.isDone() ? "1" : "0") + " | " +
                                task.getDescription() + " | " + ((Event) task).getStart() +
                                " | " + ((Event) task).getEnd();
                    } else {
                        continue;
                    }
                    bw.write(taskSave + "\n");
                }
            }
        } catch (IOException e) {
                System.out.println("Error writing tasks: " + e.getMessage());
        }
    }
}