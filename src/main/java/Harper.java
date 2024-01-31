import exceptions.HarperException;
import exceptions.HarperInvalidDeadlineException;
import exceptions.HarperInvalidInputException;
import exceptions.HarperInvalidEventException;
import exceptions.HarperInvalidFileContentFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A chatbot called Harper.
 *
 * @author gosongying
 * @version CS2103T AY23/24 Sem 2
 */
public class Harper {

    private static final String LINE = "_________________________________________________________";
    private ArrayList<Task> tasks = new ArrayList<>();
    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private static final String FILE_PATH = PROJECT_DIR + File.separator + "data" + File.separator + "harper.txt";

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println(LINE + "\n"
                + "Hello! I am Harper.\n"
                + "What can I do for you?\n"
                + LINE);
    }

    /**
     * Exit the chat.
     */
    public void exit() {
        System.out.println(LINE + "\n"
                + "Hope to see you again soon! Peace out!\n"
                + LINE);
    }

    /**
     * Lists out the tasks in the list.
     */
    public void listTasks() {
        System.out.println(LINE);
        if (this.tasks.isEmpty()) {
            System.out.println("Nothing is in your list!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println(i + 1 + ". " + this.tasks.get(i).toString());
            }
        }
        System.out.println(LINE);
    }

    /**
     * Creates a todo task with the description and adds it into the list.
     *
     * @param description Description of the task.
     */
    public void addToDo(String description) {
        try {
            Task newToDo = new ToDo(description, false);
            Harper.appendToFile(Harper.FILE_PATH, newToDo);
            this.tasks.add(newToDo);
            int listSize = this.tasks.size();
            System.out.println(LINE + "\n"
                    + "Got it. I've added this task into your list:\n"
                    + newToDo.toString() + "\n"
                    + "Now you have " + listSize + (listSize > 1 ? " tasks " : " task ") + "in the list.\n"
                    + LINE);
        } catch (IOException e) {
            Harper.createFolderAndFile("data", "harper.txt");
            this.addToDo(description);
        }

    }

    /**
     * Creates a deadline task with the description and deadline and adds it into the list.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public void addDeadline(String description, String by) {
        try {
            Task newDeadline = new Deadline(description, false, by);
            Harper.appendToFile(Harper.FILE_PATH, newDeadline);
            this.tasks.add(newDeadline);
            int listSize = this.tasks.size();
            System.out.println(LINE + "\n"
                    + "Got it. I've added this task into your list:\n"
                    + newDeadline.toString() + "\n"
                    + "Now you have " + listSize + (listSize > 1 ? " tasks " : " task ") + "in the list.\n"
                    + LINE);
        } catch (IOException e) {
            Harper.createFolderAndFile("data", "harper.txt");
        }

    }

    /**
     * Creates an event task with description, start time and end time, and adds it into the list.
     *
     * @param description Description of the task.
     * @param start Start time of the task.
     * @param end End time of the task.
     */
    public void addEvent(String description, String start, String end) {
        try {
            Task newEvent = new Event(description, false, start, end);
            Harper.appendToFile(Harper.FILE_PATH, newEvent);
            this.tasks.add(newEvent);
            int listSize = this.tasks.size();
            System.out.println(LINE + "\n"
                    + "Got it. I've added this task into your list:\n"
                    + newEvent.toString() + "\n"
                    + "Now you have " + listSize + (listSize > 1 ? " tasks " : " task ") + "in the list.\n"
                    + LINE);
        } catch (IOException e) {
            Harper.createFolderAndFile("data", "harper.txt");
        }
    }

    /**
     * Marks the task as done or not done based on the command.
     *
     * @param command Marks or unmarks the task.
     * @param taskIndex Index of the task in the list.
     */
    public void markOrUnmark(String command, int taskIndex) {
        Task taskToMark = this.tasks.get(taskIndex);
        boolean isDone = taskToMark.isDone;
        System.out.println(LINE);
        try {
            if (command.equals("mark")) {
                taskToMark.markAsDone();
                this.saveToHardDisk(Harper.FILE_PATH);
                System.out.println("Nice! I've marked this task as done:");
            }
            if (command.equals("unmark")) {
                taskToMark.markAsNotDone();
                this.saveToHardDisk(Harper.FILE_PATH);
                System.out.println("OK, I've marked this task as not done yet:");
            }
        } catch (IOException e) {
            if (command.equals("mark") && !isDone) {
                taskToMark.markAsNotDone();
            }
            if (command.equals("unmark") && isDone) {
                taskToMark.markAsDone();
            }
            System.out.println("Some errors have caused your task cannot be marked!");
        }

        System.out.println(taskToMark.toString() + "\n" + LINE);
    }

    /**
     * Informs the user that the command entered is invalid.
     */
    public void handleInvalidInput() {
        System.out.println(LINE + "\n" + "Please enter an valid input!\n" + LINE);
    }

    /**
     * Informs the user that the index entered is invalid.
     */
    public void handleIndexOutOfBounds() {
        System.out.println(LINE + "\n" + "Index is out of bounds. Please provide a valid task index!\n" + LINE);
    }

    /**
     * Handles the logic when todo is entered.
     *
     * @param input The input of user.
     */
    public void handleToDo(String input) {
        String taskDescription = input.substring("todo".length()).trim();
        this.addToDo(taskDescription);
    }

    /**
     * Handles the logic when deadline is entered.
     *
     * @param input The input of user.
     */
    public void handleDeadline(String input) {
        String taskDescriptionAndDeadline = input.substring("deadline".length()).trim();
        String[] parts = taskDescriptionAndDeadline.split("/by", 2);
        if (parts.length != 2) {
            throw new HarperInvalidDeadlineException();
        }
        String description = parts[0].trim();
        String deadline = parts[1].trim();
        if (description.isEmpty() || deadline.isEmpty()) {
            throw new HarperInvalidDeadlineException();
        }
        this.addDeadline(description, deadline);
    }

    /**
     * Handles the logic when event is entered.
     *
     * @param input The input of user.
     */
    public void handleEvent(String input) {
        String taskDescriptionAndStartEnd = input.substring("event".length()).trim();
        String[] parts = taskDescriptionAndStartEnd.split("/from", 2);
        if (parts.length != 2) {
            throw new HarperInvalidEventException();
        }
        String description = parts[0].trim();
        String[] startAndEnd = parts[1].trim().split("/to", 2);
        if (startAndEnd.length != 2 || description.isEmpty()) {
            throw new HarperInvalidEventException();
        }
        String start = startAndEnd[0].trim();
        String end = startAndEnd[1].trim();
        if (start.isEmpty() || end.isEmpty()) {
            throw new HarperInvalidEventException();
        }
        this.addEvent(description, start, end);
    }

    /**
     * Handles the logic when mark or unmark is entered.
     *
     * @param input Input of user.
     */
    public void handleMarkOrUnmark(String input) {
        String[] commands = input.split(" ", 2);
        int taskIndex = Integer.parseInt(commands[1].trim()) - 1;
        this.markOrUnmark(commands[0], taskIndex);
    }

    /**
     * Preprocesses the command before deleting the task.
     *
     * @param input Input of user.
     */
    public void handleDeleteTask(String input) {
        String[] commands = input.split(" ", 2);
        int taskIndex = Integer.parseInt(commands[1].trim()) - 1;
        this.deleteTask(taskIndex);
    }

    /**
     * Removes the task with the index specified from the list.
     *
     * @param taskIndex Index of the task in the list.
     */
    public void deleteTask(int taskIndex) {
        Task task = this.tasks.remove(taskIndex);
        try {
            this.saveToHardDisk(Harper.FILE_PATH);
            int listSize = this.tasks.size();
            System.out.println(LINE + "\n"
                    + "Ok! I've removed this task for you:\n"
                    + task.toString() + "\n"
                    + "Now you have " + listSize + (listSize > 1 ? " tasks " : " task ") + "in the list.\n"
                    + LINE);

        } catch (IOException e) {
            this.tasks.add(taskIndex, task);
            System.out.println(LINE + "\n"
                    + "Some errors have caused your task cannot be deleted!\n"
                    + LINE);
        }
    }

    private static void createFolderAndFile(String folderName, String fileName) {
        File folder = new File(PROJECT_DIR, folderName);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                File file = new File(folder, fileName);
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        System.out.println(LINE + "\n"
                                + "The file harper.txt cannot be created.\n"
                                + LINE);
                    }
                }
            }
        }
    }

    /**
     * Adds data to the end of the file specified.
     *
     * @param filePath Relative path of the file.
     * @param task Task to be added into the file.
     * @throws IOException When the file does not exist.
     */
    private static void appendToFile(String filePath, Task task) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }

    /**
     * Loads data from hard disk.
     * Transfers data into Task object and stores into the list.
     *
     * @param filePath Relative path of the file.
     * @return Success of loading.
     */
    private boolean loadFromHardDisk(String filePath) {
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] taskString = scanner.nextLine().split(" \\| ");
                if (taskString[0].equals("T")) {
                    boolean isDone = taskString[1].equals("1");
                    Task task = new ToDo(taskString[2], isDone);
                    this.tasks.add(task);
                } else if (taskString[0].equals("D")) {
                    boolean isDone = taskString[1].equals("1");
                    Task task = new Deadline(taskString[2], isDone, taskString[3]);
                    this.tasks.add(task);
                } else if (taskString[0].equals("E")) {
                    boolean isDone = taskString[1].equals("1");
                    String[] duration = taskString[3].split(" - ");
                    Task task = new Event(taskString[2], isDone, duration[0], duration[1]);
                    this.tasks.add(task);
                } else {
                    throw new HarperInvalidFileContentFormatException();
                }
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LINE + "\n"
                    + "Please make sure the content of the file harper.txt follows the expected format:\n"
                    + "ToDo: \"T | [0 or 1] | [description]\"\n"
                    + "Deadline: \"D | [0 or 1] | [description] | [by]\"\n"
                    + "Event: \"E | [0 or 1] | [description] | [start] - [end]\"\n"
                    + LINE);
            this.tasks.clear();
            return false;
        } catch (HarperInvalidFileContentFormatException e) {
            System.out.println(e.getMessage());
            this.tasks.clear();
            return false;
        } catch (FileNotFoundException e) {
            Harper.createFolderAndFile("data", "harper.txt");
            return true;
        }
    }

    /**
     * Saves all the tasks in the list into the hard disk.
     *
     * @param filePath File path to be stored.
     * @throws IOException When the file cannot be found.
     */
    private void saveToHardDisk(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : this.tasks) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Starts the chat, reads user's input and respond to user.
     * Saves user's input and displays back when requested.
     * Loads data from hard disk.
     * Marks tasks as done or not done.
     * Add different types of tasks into the list.
     */
    public void startChat() {
        this.greet();
        boolean loadSuccess = this.loadFromHardDisk(Harper.FILE_PATH);
        if (!loadSuccess) {
            this.exit();
            return;
        }
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            if (input.equals("bye")) {
                this.exit();
                scanner.close();
                break;
            }
            if (input.equals("list")) {
                this.listTasks();
                continue;
            }
            try {
                if (input.startsWith("todo ")) {
                    this.handleToDo(input);
                    continue;
                }
                if (input.startsWith("deadline ")) {
                    this.handleDeadline(input);
                    continue;
                }
                if (input.startsWith("event ")) {
                    this.handleEvent(input);
                    continue;
                }
                if (input.startsWith("mark ") || input.startsWith("unmark ")) {
                    this.handleMarkOrUnmark(input);
                    continue;
                }
                if (input.startsWith("delete ")) {
                    this.handleDeleteTask(input);
                    continue;
                }
                throw new HarperInvalidInputException();
            } catch (NumberFormatException e) {
                this.handleInvalidInput();
            } catch (IndexOutOfBoundsException e) {
                this.handleIndexOutOfBounds();
            } catch (HarperException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
