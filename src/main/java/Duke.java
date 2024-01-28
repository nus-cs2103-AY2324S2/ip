import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
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

        public TaskManager() {
            taskList = new ArrayList<>();
            loadTasksFromFile();
        }

        public void loadTasksFromFile() {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs(); // create directory if does not exist
            }
            File file = new File(directory, "duke.txt");
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error creating 'duke.txt' file: " + e.getMessage());
            }

        }
        private String formatTaskForFile(Task task) {
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
    }


    public static void listFunction() {
        TaskManager taskManager = new TaskManager();
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

                    Task newDeadline = new Deadline(description, by);
                    taskList.add(newDeadline);
                    currIndex++;
                    addMessage(newDeadline, currIndex);
                    taskManager.saveTaskToFile(newDeadline);
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

                    Task newEvent = new Event(description, from, to);
                    taskList.add(newEvent);
                    currIndex++;
                    addMessage(newEvent, currIndex);
                    taskManager.saveTaskToFile(newEvent);
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
                    } else {
                        System.out.println("Invalid task number: " + (taskIndex + 1));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid task number: " + command[1]);
                }
                break;
            default:
                System.out.println("OH NO I'm not sure what that command is. You may use the commands " +
                        "todo, deadline, list, event, mark and unmark");
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
