import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    private static final String FILE_PATH = "./data/bob.txt";

    public static void main(String[] args) {
        String greet = " Hello! I'm Bob.\n"
                + " What can I do for you?\n";

        String exit = " Bye. Hope to see you again soon!";

        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = loadFile(FILE_PATH);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(exit);
                saveFile(taskList, FILE_PATH);
                break;
            }

            else if (input.equals("list")) {
                int size = taskList.size();

                System.out.println(" Here are the tasks in your list:");

                for (int count = 0; count < size; count++) {
                    System.out.println(" " + (count + 1) + "." + taskList.get(count));
                }
            }

            else if (input.equals("clear")) {
                taskList.clear();

                System.out.println(" Your tasks have been cleared.");
            }

            else if (input.trim().matches("mark|unmark|deadline|todo|event|delete")) {
                System.out.println(" Your entry is incomplete!");
            }

            else if (input.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    Task task = taskList.get(index);
                    task.markAsDone();

                    System.out.println(" Nice! I've marked this task as done:\n"
                            + "  " + task);
                }

                catch (IndexOutOfBoundsException e) {
                    System.out.println(" There exists no such task.");
                }
            }

            else if (input.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    Task task = taskList.get(index);
                    task.markAsUndone();

                    System.out.println(" OK, I've marked this task as not done yet:\n"
                            + "  " + task);
                }

                catch (IndexOutOfBoundsException e) {
                    System.out.println(" There exists no such task.");
                }
            }

            else if (input.startsWith("deadline ")) {
                int byIndex = input.indexOf("/by ");
                String taskDescription = input.substring(9, byIndex).trim();
                String deadlineDate = input.substring(byIndex + 4).trim();

                Task newTask = new Deadline(taskDescription, deadlineDate);
                taskList.add(newTask);
                System.out.println(" Got it. I've added this task:\n"
                        + "  " + newTask + "\n"
                        + " Now you have " + taskList.size() + " tasks in the list.");
            }

            else if (input.startsWith("todo ")) {
                String taskDescription = input.substring(5);
                Task newTask = new ToDo(taskDescription);
                taskList.add(newTask);
                System.out.println(" Got it. I've added this task:\n"
                        + "  " + newTask + "\n"
                        + " Now you have " + taskList.size() + " tasks in the list.");
            }

            else if (input.startsWith("event ")) {
                int fromIndex = input.indexOf("/from ");
                int toIndex = input.indexOf("/to ");
                String taskDescription = input.substring(6, fromIndex).trim();
                String fromDate = input.substring(fromIndex + 6, toIndex).trim();
                String toDate = input.substring(toIndex + 4).trim();

                Task newTask = new Event(taskDescription, fromDate, toDate);
                taskList.add(newTask);
                System.out.println(" Got it. I've added this task:\n"
                        + "  " + newTask + "\n"
                        + " Now you have " + taskList.size() + " tasks in the list.");
            }

            else if (input.startsWith("delete ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task task = taskList.get(index);
                taskList.remove(index);

                System.out.println(" Noted. I've removed this task:\n"
                        + "  " + task + "\n"
                        + " Now you have " + taskList.size() + " tasks in the list.");
            }

            else {
                System.out.println(" Bob knows not what that means.");
            }
        }

        scanner.close();
    }

    /*
     * A method to load tasks from the specified file path.
     *
     * @parameter filePath A string of the file path.
     * @return An ArrayList of tasks parsed from the save file.
     */
    private static ArrayList<Task> loadFile(String filePath) {
        ArrayList<Task> taskList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String taskType = parts[0].trim();
                boolean isDone = parts[1].trim().equals("1");
                String taskDescription = parts[2].trim();

                Task task;

                if (taskType.equals("T")) {
                    task = new ToDo(taskDescription);
                }

                else if (taskType.equals("D")) {
                    String deadlineDate = parts[3].trim();
                    task = new Deadline(taskDescription, deadlineDate);
                }

                else if (taskType.equals("E")) {
                    String fromDate = parts[3].trim();
                    String toDate = parts[4].trim();

                    task = new Event(taskDescription, fromDate, toDate);
                }

                else {
                    throw new IllegalStateException("Unexpected value: " + taskType);
                }

                if (isDone) {
                    task.markAsDone();
                }

                taskList.add(task);
            }
        }

        catch (FileNotFoundException e) {
            File data = new File("data");
            data.mkdir();
            File bob = new File(data, "bob.txt");
            try {
                bob.createNewFile();
            }
            catch (IOException x) {
                System.out.println(x.getMessage());
            }
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("File loaded.");
        return taskList;
    }

    /*
     * A method to save the current tasks.
     *
     * @parameter taskList An ArrayList of the current tasks.
     * @parameter filePath A string representing the file path we want to save to.
     */
    private static void saveFile(ArrayList<Task> taskList, String filePath) {
        try {
            File bob = new File(filePath);
            File data = bob.getParentFile();

            if (!data.exists()) {
                boolean directoriesCreated = data.mkdirs();
                if (!directoriesCreated) {
                    System.out.println("Failed to create directories.");
                    return;
                }
                System.out.println("Directories created: " + data.getAbsolutePath());
            }

            if (!bob.exists()) {
                boolean fileCreated = bob.createNewFile();
                if (!fileCreated) {
                    System.out.println("Failed to create file.");
                    return;
                }
                System.out.println("File created: " + bob.getAbsolutePath());
            }

            FileWriter writer = new FileWriter(bob);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Task task : taskList) {
                String taskType;
                String line;
                String isDone = task.getIsDone() ? "1" : "0";
                String description = task.getDescription();

                if (task instanceof Deadline) {
                    taskType = "D";
                    String deadline = ((Deadline) task).getBy();
                    line = String.format("%s | %s | %s | %s", taskType, isDone, description, deadline);
                }

                else if (task instanceof Event) {
                    taskType = "E";
                    String from = ((Event) task).getFrom();
                    String to = ((Event) task).getTo();
                    line = String.format("%s | %s | %s | %s | %s", taskType, isDone, description, from, to);
                }

                else {
                    taskType = "T";
                    line = String.format("%s | %s | %s", taskType, isDone, description);
                }

                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("File saved.");
        }

        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

/*
 * This class represents a task we want to record.
 */
class Task {
    private String description;
    private boolean isDone;

    /*
     * A constructor that depicts a new task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
     * A method that marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /*
     * A method that will undo the mark on a task.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /*
     * A method that returns the isDone boolean.
     *
     * @return A boolean depending on whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /*
     * A method that returns the description.
     *
     * @return A string of the task description.
     */
    public String getDescription() {
        return this.description;
    }

    /*
     * A method that returns the task status as a string.
     *
     * @return A check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

class Deadline extends Task {
    protected String by;

    /*
     * A constructor for creating a new task with a deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /*
     * A method to get by.
     *
     * @return The deadline due.
     */
    public String getBy() {
        return this.by;
    }

    /*
     * A method that returns the task status as a string.
     *
     * @return A label [D] and a check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class ToDo extends Task {
    /*
     * A constructor for creating a new todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /*
     * A method that returns the task description.
     *
     * @return A label [T] and a check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    /*
     * A constructor to create a new event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /*
     * A method to get from.
     *
     * @return The start date of the event.
     */
    public String getFrom() {
        return this.from;
    }

    /*
     * A method to get to.
     *
     * @return The end date of the event.
     */
    public String getTo() {
        return this.to;
    }

    /*
     * A method that returns the status of the task.
     *
     * @return A label [E] and a check-box followed by the description of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}