import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void displayLine() {
        System.out.println(gap() + "_".repeat(50));
    }
    public static String gap() {
        return "    ";
    }
    public static void wrapInLines(String line){
        displayLine();
        System.out.println(line);
        displayLine();
    }
    public static void echo(String line) {
        wrapInLines(line);
    }
    public static void greet() {
        wrapInLines(gap() + "Hello! I'm Shirmin" + "\n"
                + gap() + "What can I do for you?");
    }
    public static void exit() {
        wrapInLines(gap() +"Bye. Hope to see you again soon!");
    }


    static ArrayList<Task> taskList = new ArrayList<>();
    static int currIndex = 0;
    enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID;
        static CommandType getCommandType(String command) {
            try {
                return valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                return INVALID;
            }
        }
    }

    public static class TaskManager {
        private ArrayList<Task> taskList;
        //private final String FILE_PATH = "./data/duke.txt";

        public TaskManager(ArrayList<Task> taskList) {
            this.taskList = taskList;
            loadTasksFromFile();
            currIndex = taskList.size();
        }

        public void loadTasksFromFile() {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs(); // create directory if does not exist
            }
            File file = new File(directory, "duke.txt");
            try {
                file.createNewFile();
                Scanner fileScanner = new Scanner(file);
                int lines = 0;
                while (fileScanner.hasNext()) {
                    String taskLine = fileScanner.nextLine();
                    Task task = parseTask(taskLine); // Implement this method based on your task format
                    if (task != null) {
                        taskList.add(task);
                    }
                    lines++;
                }
                System.out.println("    I've loaded " + lines + " tasks from Duke.txt!");
                displayLine();
            } catch(IOException e){
                    System.err.println("Error creating 'duke.txt' file: " + e.getMessage());
            }

        }

        private Task parseTask(String line) {
            String[] parts = line.split(" \\| ");
            if (parts.length < 3) {
                System.err.println("Task in file not in correct format or missing parts: " + line);
                return null;
            }

            try {
                String type = parts[0];
                boolean isDone = parts[1].trim().equals("1");
                String description = parts[2].trim();

                switch (type) {
                case "T":
                    Todo todo = new Todo(description);
                    if (isDone) {
                        todo.markDone();
                    }
                    return todo;
                case "D":
                    if (parts.length < 4) {
                        System.err.println("Deadline task missing 'by' part: " + line);
                        return null;
                    }
                    String by = parts[3].trim();
                    Deadline deadline = new Deadline(description, parseDateString(by));
                    if (isDone) {
                        deadline.markDone();
                    }
                    return deadline;
                case "E":
                    if (parts.length < 5) {
                        System.err.println("Event task missing 'from' or 'to' parts: " + line);
                        return null;
                    }
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    Event event = new Event(description, parseDateString(from), parseDateString(to));
                    if (isDone) event.markDone();
                    return event;
                default:
                    System.err.println("Unknown task type: " + type);
                    return null;
                }
            } catch (Exception e) {
                System.err.println("Error parsing task from file: " + e.getMessage() + " - Line: " + line);
                return null;
            }
        }
        private String parseDateString(String dateString) throws DateTimeParseException{
            try {
                LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
                return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                System.err.println("Error parsing date: " + dateString + ". Please use the format 'yyyy-MM-dd HHmm'.");
                return null;
            }
        }
        public String formatTaskForFile(Task task) {
            String returnString = "";
            String type =
                    task instanceof Todo ? "T" :
                    task instanceof Deadline ? "D" :
                            task instanceof Event ? "E" : "";
            int status = task.isDone();
            String details = task.getDescription();
            returnString += type + " | " + status + " | " + details;
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                returnString += " | " + deadline.getBy();
            } else if (task instanceof Event) {
                Event event = (Event) task;
                returnString += " | " + event.getFrom() + " | " + event.getTo();
            }
            return returnString;
        }
        public void saveTaskToFile(Task task) {
            try {
                FileWriter fileWriter = new FileWriter("./data/duke.txt", true);
                String taskLine = formatTaskForFile(task);
                fileWriter.write(taskLine + "\n");
                fileWriter.close();
            } catch (IOException error) {
                System.err.println("Error saving task to file: " + error.toString());
            }

        }
        public void saveAllTasksToFile() {
            // overwrites all files for mark/unmark functions
            try {
                FileWriter fileWriter = new FileWriter("./data/duke.txt", false);
                for (Task task : taskList) {
                    String taskLine = formatTaskForFile(task);
                    fileWriter.write(taskLine + "\n");
                }
                fileWriter.close();
            } catch (IOException error) {
                System.err.println("Error saving tasks to file: " + error.toString());
            }
        }
    }


    public static void listFunction() {
        TaskManager taskManager = new TaskManager(taskList);
        Scanner scanner = new Scanner(System.in);
        String currLine = scanner.nextLine();
        while(!currLine.equals("bye")) {
            String[] command = currLine.split(" ", 2);
            CommandType commandType = CommandType.getCommandType(command[0]);
            switch (commandType) {
            case LIST:
                displayList(taskList);
                break;
            case MARK:
                try {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    if (taskIndex < currIndex) {
                        taskList.get(taskIndex).markDone();
                        // displayLine();
                        System.out.println(gap() + "Nice! I've marked this task as done:");
                        System.out.println(gap() + gap() + taskList.get(taskIndex));
                        displayLine();
                        taskManager.saveAllTasksToFile();
                        break;
                    } else { // out of range
                        System.out.println("invalid, out of range");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number: " + command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There are " + currIndex + "numbers, please enter a number from 1 to " + currIndex);
                }
                break;
            case UNMARK:
                try {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    if (taskIndex < currIndex) {
                        taskList.get(taskIndex).markUndone();
                        displayLine();
                        System.out.println(gap() + "OK, I've marked this task as not done yet:");
                        System.out.println(gap() + gap() + taskList.get(taskIndex));
                        displayLine();
                        taskManager.saveAllTasksToFile();
                        break;
                    } else { // out of range
                        System.out.println("invalid, out of range");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number: " + command[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("There are " + currIndex + " items, please enter a number from 1 to " + currIndex);
                }

                break;
            case TODO:
                try {
                    Task newTodo = new Todo(command[1]);
                    taskList.add(newTodo);
                    // taskList[currIndex] = newTodo;
                    currIndex++;
                    addMessage(newTodo, currIndex);
                    taskManager.saveTaskToFile(newTodo);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("oopsy doopsy you made a -ucky wucky! The description of a todo cannot be empty.");
                }

                break;
            case DEADLINE:
                try {
                    String[] details = command[1].split(" /by ");
                    String description = details[0];
                    String by = details[1];

                    Deadline newDeadline = new Deadline(description, by);
                    if (newDeadline.hasValidDate()) {
                        taskList.add(newDeadline);
                        currIndex++;
                        addMessage(newDeadline, currIndex);
                        taskManager.saveTaskToFile(newDeadline);
                    } else {
                        System.out.println("Error creating deadline task. Please " +
                                "provide a valid date in the format 'yyyy-MM-dd HHmm'.");
                        displayLine();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("oopsy doopsy you made a -ucky wucky! The description of a deadline" +
                            " must be in the format 'deadline [task] /by [time]'.");
                }
                break;
            case EVENT:
                try {
                    String[] details = command[1].split(" /from ");
                    String description = details[0];
                    String[] fromTo = details[1].split(" /to ");
                    String from = fromTo[0];
                    String to = fromTo[1];

                    Event newEvent = new Event(description, from, to);
                    if (newEvent.hasValidDate()) {
                        taskList.add(newEvent);
                        currIndex++;
                        addMessage(newEvent, currIndex);
                        taskManager.saveTaskToFile(newEvent);
                    } else {
                        System.out.println("Error creating event task. Please " +
                                "provide a valid from and valid to date in the format 'yyyy-MM-dd HHmm'.");
                        displayLine();
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("oopsy doopsy you made a -ucky wucky! The description of a deadline" +
                            " must be in the format 'deadline [task] /from [time]' /to [time].");
                }
                break;
            case DELETE:
                try {
                    int taskIndex = Integer.parseInt(command[1]) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        Task removedTask = taskList.remove(taskIndex);
                        displayLine();
                        System.out.println(gap() + "Ok, I've removed the task:");
                        System.out.println(gap() + gap() + removedTask);
                        System.out.println(gap() + "You have " + taskList.size() + " tasks remaining in the list.");
                        displayLine();
                        taskManager.saveAllTasksToFile();
                        currIndex--;
                    } else {
                        System.out.println("Invalid task number: " + (taskIndex + 1));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number: " + command[1]);
                }
                break;
            default:
                System.out.println("OH NO I'm not sure what that command is. You may use the commands " +
                        "todo, deadline, list, event, delete, mark and unmark");
                break;
            }
            currLine = scanner.nextLine();
        }

    }

    public static <T extends Task> void addMessage(T task, Integer number){
        // displayLine();
        System.out.println(gap() + "Got it. I've added this task:");
        System.out.println(gap() + gap() + task.toString());
        System.out.println(gap() + "Now you have " + number.toString() + " tasks in the list.");
        displayLine();
    }

    public static void displayList(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println(gap() + "There are no tasks in your list.");
        } else {
            System.out.println(gap() + "Here are the tasks in your list:");
            int i = 1;
            for (Task t : list) {
                System.out.println(gap() + i + "." + t.toString());
                i++;
            }
        }
        displayLine();
    }


    public static void main(String[] args){
        greet();
        listFunction();
        exit();
    }
}


//                switch (commandType) {
//                case LIST:
//                    break;
//                case MARK:
//                    break;
//                case UNMARK:
//                    break;
//                case TODO:
//                    break;
//                case DEADLINE:
//                    break;
//                case EVENT:
//                    break;
//                case DELETE:
//                    break;
