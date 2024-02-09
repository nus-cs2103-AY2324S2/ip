import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Whisper {
    static String line = "-------------------------------------------------\n";
    static String name = "Whisper";
    static String welcomeMsg = "Hello! I'm " + name + " , your personal chatbot!\n" +
            "What can I do for you?\n";
    static String byeMsg = line + "Bye. Hope to see you soon!\n" + line;
    static ArrayList<Task> taskList; // store tasks in ArrayList

    // path for task file
    private static final String FILE_PATH = "./data/whisper.txt";

    // Main method
    public static void main(String[] args) throws IOException, WhisperException {
        System.out.println(line + welcomeMsg + line);

        Scanner sc = new Scanner(System.in);
        taskList = new ArrayList<>();

        try {
            loadTaskFile(); // load tasks from file
        } catch (FileNotFoundException | WhisperException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            // read user input
            System.out.println("Enter your input:");
            String input = sc.nextLine();

            try {
                // break if user exists the bot
                if (input.equalsIgnoreCase("bye")) {
                    saveFile(); // save tasks before exit
                    System.out.println(byeMsg);
                    break;
                    // show user's list
                } else if (input.equalsIgnoreCase("list")) {
                    getTasks();
                    // store user list and display them back
                } else if (input.toLowerCase().startsWith("mark")) {
                    // get the index of task
                    int index = getIndex(input);
                    printTaskDone(index);
                } else if (input.toLowerCase().startsWith("unmark")) {
                    int index = getIndex(input);
                    printTaskUndone(index);
                } else if (input.startsWith("todo")) {
                    addTodo(input);
                } else if (input.startsWith("event")) {
                    addEvent(input);
                } else if (input.startsWith("deadline")) {
                    addDeadline(input);
                } else if (input.startsWith("delete")) {
                    int index = getIndex(input);
                    deleteTask(index);
                } else {
                    throw WhisperException.unknownCommand();
                }
                //saveFile(); // saves tasks to file when task list changes
            } catch (WhisperException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }

    // Load file
    public static void loadTaskFile() throws FileNotFoundException, WhisperException {
//        try (Scanner sc = new Scanner(new File(FILE_PATH))) {
        try (Scanner sc = new Scanner(new File(FILE_PATH))) {
            while (sc.hasNextLine()) {
                String input = sc.nextLine().trim();
                Task task = parseTaskFromString(input);
                taskList.add(task);
            }
            System.out.println("File loaded successfully.");
        } catch (FileNotFoundException e) {
                System.out.println("Error loading the file: \n" + e.getMessage());
                e.printStackTrace();
        }
    }


    // save task to file when task list changes
    public static void saveFile() {
        try (PrintWriter pw = new PrintWriter(FILE_PATH)) {
            for (Task task : taskList) {
                pw.println(taskToFormattedString(task));
            }
            System.out.println("\nTasks saved succesfully\n");
        } catch (FileNotFoundException e) {
            System.out.println("Error: Save task failed. \n" + e.getMessage());
        }
    }

    // formats the logic
    private static String taskToFormattedString(Task task) {
        StringBuilder formattedTask = new StringBuilder();
        formattedTask.append(task.getTaskCat().name()).append(" | ")
                .append(task.isDone ? "1" : "0").append(" | ")
                .append(task.getDescription());

//        if (task.getTaskCat() == Task.TaskCategory.D) {
//            formattedTask.append(" | ").append(task.getBy());
//        } else if (task.getTaskCat() == Task.TaskCategory.E) {
//            formattedTask.append(" | ").append(task.getFrom()).append("-").append(task.getTo());
//        }

        switch (task.getTaskCat()) {
        case D:
            formattedTask.append(" | ").append(task.getDeadline().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            break;
        case E:
            formattedTask.append(" | ")
                    .append(task.getEventFromDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .append("-")
                    .append(task.getEventToDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            break;
        }


        return formattedTask.toString();
    }

    // parse task from string
    private static Task parseTaskFromString(String taskInput) throws WhisperException {
        try {
            String[] parts = taskInput.split("\\|");

            if (parts.length < 3) {
                throw WhisperException.invalidFileFormat();
            }

            String taskType = parts[0].trim();
            int isDone = Integer.parseInt(parts[1].trim());
            String description = parts[2].trim();

            Task task;

            switch(taskType) {
            case "T":
                task = new Task(description, Task.TaskCategory.T);
                break;
            case "D":
                if (parts.length < 4) {
                    throw WhisperException.invalidFileFormat();
                }
                String by = parts[3].trim();
                LocalDateTime deadlineDateTime = parseDateTime(by);
                //task = new Task(description, Task.TaskCategory.D).setBy(deadlineDateTime);
                task = new Task(description, Task.TaskCategory.D, deadlineDateTime);
                break;
            case "E":
                if (parts.length < 4) {
                    throw WhisperException.invalidFileFormat();
                }
                String dateTime = parts[3].trim();
                String[] timeParts = dateTime.split("-");

                if (timeParts.length == 2) {
                    String from = timeParts[0];
                    String to = timeParts[1];
                    LocalDateTime fromDateTime = parseDateTime(from);
                    LocalDateTime toDateTime = parseDateTime(to);

                    //task = new Task(description, Task.TaskCategory.E).setFrom(from).setTo(to);
                    task = new Task(description, Task.TaskCategory.E, fromDateTime, toDateTime);
                } else {
                    throw WhisperException.invalidFileFormat();
                }
                break;
            default:
                throw WhisperException.invalidFileFormat();
            }

            if (isDone == 1) {
                task.markAsDone();
            }
            return task;
        } catch (WhisperException | NumberFormatException | IndexOutOfBoundsException e) {
            throw new WhisperException("Error parsing task from file.");
        }
    }

    // insert task (todo) into the task list
    public static void addTodo(String description) throws WhisperException {
        try {
            checkTaskListFull();

            if (description.trim().equalsIgnoreCase("todo")) {
                // without description
                throw WhisperException.invalidTodo();
            }

            Task todo = new Task(description, Task.TaskCategory.T);
            taskList.add(todo);
            System.out.println(line + "Got it. I've added this task:\n" + todo.toString() + "\n" +
                    printTaskCount(taskList.size()) + "\n" + line);

        } catch (NumberFormatException e) {
            //System.out.println("Sorry, list is full. Can't add anymore.");
            // handle unexpected exception
            throw new WhisperException("Unexpected error occurred.");
        }
    }

    // add new Event
    public static void addEvent(String description) throws WhisperException {
        try {
            checkTaskListFull(); // check if the list is full before adding

            String[] eventStr = description.split("/from", 2); // create two substring after "from"
            // check if input format is correct
            if (eventStr.length < 2) {
                throw WhisperException.invalidEvent();
            }

            // check description format
            String[] descr = eventStr[0].split(" ", 2);
            // Check if it split two parts and the 2nd part is not empty
            if (descr.length < 2 || descr[1].trim().isEmpty()) {
                throw WhisperException.invalidEvent();
            }

            // extract event name
            String eventName = descr[1];
            String[] time = eventStr[1].split("/to", 2); // create two substring & get from to timing
            // Check if it splits in two parts
            if (time.length < 2) {
                throw WhisperException.invalidEvent();
            }

            // extract time
            String from = time[0].trim();
            String to = time[1].trim();
            // Check if "from" and "to" are not empty
            if (from.isEmpty() || to.isEmpty()) {
                throw WhisperException.invalidEvent();
            }

            // parse event date and time
            LocalDateTime fromDateTime = parseDateTime(from);
            LocalDateTime toDateTime = parseDateTime(to);
            System.out.println("fromDateTime: " + fromDateTime);
            System.out.println("toDateTime: " + toDateTime);

            // add new event to task list
            //Task event = new Task(eventName, Task.TaskCategory.E).setFrom(from).setTo(to);
            Task event = new Task(eventName, Task.TaskCategory.E, fromDateTime, toDateTime);
            taskList.add(event);
            System.out.println(line + "Got it. I've added this task:\n" + event.toString() +
                    "\n" + printTaskCount(taskList.size()) + "\n" + line);
        } catch (WhisperException e) {
            System.out.println(e.getMessage());
        }
    }

    // add deadline to tasklist
    public static void addDeadline(String description) {
        try {
            checkTaskListFull(); // check if list is full

            String[] deadlineStr = description.split("/by", 2);
            if (deadlineStr.length < 2) {
                throw WhisperException.invalidDeadline();
            }

            String[] descr = deadlineStr[0].split(" ", 2);
            if (descr.length < 2) {
                throw WhisperException.invalidDeadline();
            }

            String deadlineName = descr[1];
            String by = deadlineStr[1].trim();

            // check if description or time is empty
            if (deadlineName.equals("") | by.equals("")) {
                throw WhisperException.invalidDeadline();
            }

            // parse date and time
            LocalDateTime deadlineDateTime = parseDateTime(by);

            // add new deadline to task list
            //Task deadline = new Task(deadlineName, Task.TaskCategory.D).setBy(by);
            Task deadline = new Task(deadlineName, Task.TaskCategory.D, deadlineDateTime);
            taskList.add(deadline);
            System.out.println(line + "Got it. I've added this task:\n" + deadline.toString() + "\n" +
                    printTaskCount(taskList.size()) + "\n" + line);
        } catch (WhisperException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    // parse date and time
    private static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }


    // display text when task is marked done
    public static void printTaskDone(int index) throws WhisperException {
        try {
            // check index bound
            if (index >= 0 && index < taskList.size()) {
                Task t = taskList.get(index);
                t.markAsDone();
                System.out.println(line + "Nice! I've marked this task as done:\n" + t.toString() + "\n" + line);
                saveFile(); // save changes to file
            } else {
                throw WhisperException.invalidTaskID();
            }
        } catch (WhisperException e) {
            System.out.println(e.getMessage());
        }
    }

    // display text when task mark undone
    public static void printTaskUndone(int index) throws WhisperException {
        try {
            // check index bound
            if (index >= 0 && index < taskList.size()) {
                Task t = taskList.get(index);
                t.markAsUndone();
                System.out.println(line + "Nice! I've marked this task as not done:\n" + t.toString() + "\n" + line);
                saveFile(); // save changes to file
            } else {
                throw WhisperException.invalidTaskID();
            }
        } catch (WhisperException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String printTaskCount(int count) {
        return "Now you have " + taskList.size() + " tasks in the list.";
    }

    // display task list
    public static void getTasks() {
        System.out.println(line + "Here are your task list:\n");

        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println((i + 1) + ". " + currentTask);
        }
        System.out.println(line);
    }

    // get task id based on command
    public static int getIndex(String input) throws WhisperException {
        try {
            return Integer.parseInt(input.substring(input.indexOf(" ") + 1).trim()) - 1;
        } catch (NumberFormatException e) {
            throw WhisperException.unknownCommand();
        }
    }

    // check if task list has more than 100 tasks
    public static void checkTaskListFull() throws WhisperException {
        if (taskList.size() >= 100) {
            throw WhisperException.taskFull();
        }
    }

    // check if index is valid
    public static void checkIndex(int index) throws WhisperException {
        if (index < 0 || index >= taskList.size()) {
            throw WhisperException.invalidTaskID();
        }
    }

    // Delete task method
    public static void deleteTask(int index) throws WhisperException {
        try {
            checkIndex(index);
            Task deletedTask = taskList.remove(index);
            System.out.println(line + "Noted! I've marked removed this task:\n" + deletedTask.toString() + "\n" +
                    printTaskCount(taskList.size()) + "\n" + line);
        } catch (WhisperException e) {
            System.out.println(e.getMessage());
        }
    }
}