import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a chatbot that assists the user with managing a task list.
 * This chatbot provides functionality to add, delete, and view tasks.
 * Additionally, the chatbot can update task statuses.
 */
public class Duke {
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Prints horizontal line.
     */
    static void breakLine() {
        System.out.println("---------------------------------------");
    }

    /**
     * Prints a greeting to the user.
     */
    static void greet() {
        breakLine();
        System.out.println("Hello! I'm teletubby"  + "\nWhat can I do for you?");
        breakLine();
    }

    /**
     * Prints a goodbye message to the user.
     */
    static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        breakLine();
    }

    /**
     * Adds a task to the task list.
     *
     * @param input Task to be added.
     * @throws DukeException If the task details are missing or the command is not supported.
     */
    static void add(String input) throws DukeException {
        breakLine();

        Task taskAdded;
        if (input.startsWith("todo")) {
            if (input.length() < 6) {
                throw new DukeException("do what?");
            }
            ToDo td = new ToDo(input.substring(5));
            tasks.add(td);
            taskAdded = td;
        } else if (input.startsWith("deadline")) {
            if (input.length() < 12) {
                throw new DukeException("what's the task?");
            }
            String by = input.split("/by ", 2)[1];
            String description = input.split(" ", 2)[1].split(" /by")[0];
            Deadline d = new Deadline(description, by);
            tasks.add(d);
            taskAdded = d;
        } else if (input.startsWith("event")) {
            String description = input.split(" ", 2)[1].split(" /from")[0];
            String start = input.split("/from ",2)[1].split(" /to")[0];
            String end = input.split("/to ")[1];
            Event e = new Event(description, start, end);
            tasks.add(e);
            taskAdded = e;
        } else {
            throw new DukeException("gong mat yeh");
        }
        int numItems = tasks.size();
        String sOrP = numItems == 1 ? "task" : "tasks";
        System.out.println("Got it. I've added this task:\n" + taskAdded.toString()
                + "\nNow you have " + numItems + " " + sOrP +" in the list.");
        breakLine();
    }

    /**
     * Prints all tasks in the task list including task name, detail, and status.
     */
    static void list() {
        breakLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.printf("%d. %s\n", i+1, t.toString());
        }
        breakLine();
    }

    /**
     * Removes task of specified index from the task list.
     *
     * @param input Text input of delete statement.
     * @throws DukeException If index is missing or invalid.
     */
    static void delete(String input) throws DukeException {
        breakLine();
        String taskNumber = input.replaceAll("delete", "").replaceAll(" ","");
        if (taskNumber.length() < 1) {
            throw new DukeException("which task?");
        }
        try {
            int task = Integer.parseInt(taskNumber);
            Task removedTask = tasks.get(task-1);
            tasks.remove(task -1);
            int numItems = tasks.size();
            String sOrP = numItems == 1 ? "task" : "tasks";
            System.out.println("Noted. I've removed this task:\n" + removedTask.toString()
                    + "Now you have " + numItems + " " + sOrP + " in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("invalid task number... count properly xx");
        }
        breakLine();
    }

    /**
     * Creates a data directory and duke.txt file if not found.
     */
    static void initializeFile() {
        String filePath = "./data/duke.txt";
        File f = new File(filePath);
        try {
            f.getParentFile().mkdirs();
            boolean isDirCreated = f.createNewFile();
            boolean isFileCreated = f.createNewFile();
            if (!isFileCreated) {
                readFromFile(f);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
        }
    }

    /**
     * Reads content saved in the specified file to fill task list.
     *
     * @param f File to be read.
     */
    static void readFromFile(File f) {
        try {
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                String[] details = task.split("\\|");
                String taskType = details[0];
                Task taskRead = null;
                switch (taskType) {
                case "T":
                    ToDo td = new ToDo(details[2]);
                    tasks.add(td);
                    taskRead = td;
                    break;
                case "D":
                    Deadline d = new Deadline(details[2], details[3]);
                    tasks.add(d);
                    taskRead = d;
                    break;
                case "E":
                    Event e = new Event(details[2], details[3], details[4]);
                    tasks.add(e);
                    taskRead = e;
                    break;
                }
                if (details[1].equals("X")) {
                    taskRead.markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes task list content to duke.txt.
     */
    static void writeToFile() {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                if (curr instanceof ToDo) {
                    ToDo temp = (ToDo) curr;
                    fw.write(temp.getFileFormat());
                } else if (curr instanceof Deadline) {
                    Deadline temp = (Deadline) curr;
                    fw.write(temp.getFileFormat());
                } else if (curr instanceof Event){
                    Event temp = (Event) curr;
                    fw.write(temp.getFileFormat());
                }
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

    /**
     * Starts the chatbot and scans for user input until the user says bye.
     */
    public static void main(String[] args) {
        initializeFile();
        greet();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                breakLine();
                exit();
                break;
            } else if (input.equals("list")) {
                list();
            } else if (input.startsWith("mark")) {
                try {
                    String[] parts = input.split("mark ");
                    if (parts.length < 2) {
                        throw new DukeException("Missing task number...");
                    }
                    int taskNumber = Integer.parseInt(parts[1]);
                    tasks.get(taskNumber - 1).markAsDone();
                } catch (DukeException a) {
                    breakLine();
                    System.out.println(a.getMessage());
                    breakLine();
                } catch (IndexOutOfBoundsException b) {
                    breakLine();
                    System.out.println("Invalid task number... count properly xx");
                    breakLine();
                }
            } else if (input.startsWith("unmark")) {
                try {
                    String[] parts = input.split("unmark ");
                    if (parts.length < 2) {
                        throw new DukeException("Missing task number...");
                    }
                    int taskNumber = Integer.parseInt(parts[1]);
                    tasks.get(taskNumber - 1).unmark();
                } catch (DukeException a) {
                    breakLine();
                    System.out.println(a.getMessage());
                    breakLine();
                } catch (IndexOutOfBoundsException b) {
                    breakLine();
                    System.out.println("Invalid task number... count properly xx");
                    breakLine();
                }
            } else if (input.startsWith("delete")) {
                try {
                    delete(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    breakLine();
                }
            } else {
                try {
                    add(input);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    breakLine();
                } catch (IndexOutOfBoundsException a) {
                    System.out.println("please follow input format...");
                    breakLine();
                }
            }
        }
        writeToFile();
    }
}
